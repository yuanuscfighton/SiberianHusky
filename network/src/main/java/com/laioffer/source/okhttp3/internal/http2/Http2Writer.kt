package com.laioffer.source.okhttp3.internal.http2

import com.laioffer.source.okhttp3.internal.format
import com.laioffer.source.okhttp3.internal.http2.Http2.CONNECTION_PREFACE
import com.laioffer.source.okhttp3.internal.http2.Http2.FLAG_ACK
import com.laioffer.source.okhttp3.internal.http2.Http2.FLAG_END_HEADERS
import com.laioffer.source.okhttp3.internal.http2.Http2.FLAG_END_STREAM
import com.laioffer.source.okhttp3.internal.http2.Http2.FLAG_NONE
import com.laioffer.source.okhttp3.internal.http2.Http2.INITIAL_MAX_FRAME_SIZE
import com.laioffer.source.okhttp3.internal.http2.Http2.TYPE_CONTINUATION
import com.laioffer.source.okhttp3.internal.http2.Http2.TYPE_DATA
import com.laioffer.source.okhttp3.internal.http2.Http2.TYPE_GOAWAY
import com.laioffer.source.okhttp3.internal.http2.Http2.TYPE_HEADERS
import com.laioffer.source.okhttp3.internal.http2.Http2.TYPE_PING
import com.laioffer.source.okhttp3.internal.http2.Http2.TYPE_PUSH_PROMISE
import com.laioffer.source.okhttp3.internal.http2.Http2.TYPE_RST_STREAM
import com.laioffer.source.okhttp3.internal.http2.Http2.TYPE_SETTINGS
import com.laioffer.source.okhttp3.internal.http2.Http2.TYPE_WINDOW_UPDATE
import com.laioffer.source.okhttp3.internal.http2.Http2.frameLog
import com.laioffer.source.okhttp3.internal.writeMedium
import okio.Buffer
import okio.BufferedSink
import java.io.Closeable
import java.io.IOException
import java.util.logging.Level.FINE
import java.util.logging.Logger

/** Writes HTTP/2 transport frames. */
@Suppress("NAME_SHADOWING")
class Http2Writer(
  private val sink: BufferedSink,
  private val client: Boolean
) : Closeable {
  private val hpackBuffer: Buffer = Buffer()
  private var maxFrameSize: Int = INITIAL_MAX_FRAME_SIZE
  private var closed: Boolean = false
  val hpackWriter: Hpack.Writer = Hpack.Writer(out = hpackBuffer)

  @Synchronized @Throws(IOException::class)
  fun connectionPreface() {
    if (closed) throw IOException("closed")
    if (!client) return // Nothing to write; servers don't send connection headers!
    if (logger.isLoggable(FINE)) {
      logger.fine(format(">> CONNECTION ${CONNECTION_PREFACE.hex()}"))
    }
    sink.write(CONNECTION_PREFACE)
    sink.flush()
  }

  /** Applies `peerSettings` and then sends a settings ACK. */
  @Synchronized @Throws(IOException::class)
  fun applyAndAckSettings(peerSettings: Settings) {
    if (closed) throw IOException("closed")
    this.maxFrameSize = peerSettings.getMaxFrameSize(maxFrameSize)
    if (peerSettings.headerTableSize != -1) {
      hpackWriter.resizeHeaderTable(peerSettings.headerTableSize)
    }
    frameHeader(
        streamId = 0,
        length = 0,
        type = TYPE_SETTINGS,
        flags = FLAG_ACK
    )
    sink.flush()
  }

  /**
   * HTTP/2 only. Send a push promise header block.
   *
   * A push promise contains all the headers that pertain to a server-initiated request, and a
   * `promisedStreamId` to which response frames will be delivered. Push promise frames are sent as
   * a part of the response to `streamId`. The `promisedStreamId` has a priority of one greater than
   * `streamId`.
   *
   * @param streamId client-initiated stream ID.  Must be an odd number.
   * @param promisedStreamId server-initiated stream ID.  Must be an even number.
   * @param requestHeaders minimally includes `:method`, `:scheme`, `:authority`, and `:path`.
   */
  @Synchronized @Throws(IOException::class)
  fun pushPromise(
    streamId: Int,
    promisedStreamId: Int,
    requestHeaders: List<Header>
  ) {
    if (closed) throw IOException("closed")
    hpackWriter.writeHeaders(requestHeaders)

    val byteCount = hpackBuffer.size
    val length = minOf(maxFrameSize - 4L, byteCount).toInt()
    frameHeader(
        streamId = streamId,
        length = length + 4,
        type = TYPE_PUSH_PROMISE,
        flags = if (byteCount == length.toLong()) FLAG_END_HEADERS else 0
    )
    sink.writeInt(promisedStreamId and 0x7fffffff)
    sink.write(hpackBuffer, length.toLong())

    if (byteCount > length) writeContinuationFrames(streamId, byteCount - length)
  }

  @Synchronized @Throws(IOException::class)
  fun flush() {
    if (closed) throw IOException("closed")
    sink.flush()
  }

  @Synchronized @Throws(IOException::class)
  fun rstStream(streamId: Int, errorCode: ErrorCode) {
    if (closed) throw IOException("closed")
    require(errorCode.httpCode != -1)

    frameHeader(
        streamId = streamId,
        length = 4,
        type = TYPE_RST_STREAM,
        flags = FLAG_NONE
    )
    sink.writeInt(errorCode.httpCode)
    sink.flush()
  }

  /** The maximum size of bytes that may be sent in a single call to [data]. */
  fun maxDataLength(): Int = maxFrameSize

  /**
   * `source.length` may be longer than the max length of the variant's data frame. Implementations
   * must send multiple frames as necessary.
   *
   * @param source the buffer to draw bytes from. May be null if byteCount is 0.
   * @param byteCount must be between 0 and the minimum of `source.length` and [maxDataLength].
   */
  @Synchronized @Throws(IOException::class)
  fun data(outFinished: Boolean, streamId: Int, source: Buffer?, byteCount: Int) {
    if (closed) throw IOException("closed")
    var flags = FLAG_NONE
    if (outFinished) flags = flags or FLAG_END_STREAM
    dataFrame(streamId, flags, source, byteCount)
  }

  @Throws(IOException::class)
  fun dataFrame(streamId: Int, flags: Int, buffer: Buffer?, byteCount: Int) {
    frameHeader(
        streamId = streamId,
        length = byteCount,
        type = TYPE_DATA,
        flags = flags
    )
    if (byteCount > 0) {
      sink.write(buffer!!, byteCount.toLong())
    }
  }

  /** Write okhttp's settings to the peer. */
  @Synchronized @Throws(IOException::class)
  fun settings(settings: Settings) {
    if (closed) throw IOException("closed")
    frameHeader(
        streamId = 0,
        length = settings.size() * 6,
        type = TYPE_SETTINGS,
        flags = FLAG_NONE
    )
    for (i in 0 until Settings.COUNT) {
      if (!settings.isSet(i)) continue
      val id = when (i) {
        4 -> 3 // SETTINGS_MAX_CONCURRENT_STREAMS renumbered.
        7 -> 4 // SETTINGS_INITIAL_WINDOW_SIZE renumbered.
        else -> i
      }
      sink.writeShort(id)
      sink.writeInt(settings[i])
    }
    sink.flush()
  }

  /**
   * Send a connection-level ping to the peer. `ack` indicates this is a reply. The data in
   * `payload1` and `payload2` opaque binary, and there are no rules on the content.
   */
  @Synchronized @Throws(IOException::class)
  fun ping(ack: Boolean, payload1: Int, payload2: Int) {
    if (closed) throw IOException("closed")
    frameHeader(
        streamId = 0,
        length = 8,
        type = TYPE_PING,
        flags = if (ack) FLAG_ACK else FLAG_NONE
    )
    sink.writeInt(payload1)
    sink.writeInt(payload2)
    sink.flush()
  }

  /**
   * Tell the peer to stop creating streams and that we last processed `lastGoodStreamId`, or zero
   * if no streams were processed.
   *
   * @param lastGoodStreamId the last stream ID processed, or zero if no streams were processed.
   * @param errorCode reason for closing the connection.
   * @param debugData only valid for HTTP/2; opaque debug data to send.
   */
  @Synchronized @Throws(IOException::class)
  fun goAway(lastGoodStreamId: Int, errorCode: ErrorCode, debugData: ByteArray) {
    if (closed) throw IOException("closed")
    require(errorCode.httpCode != -1) { "errorCode.httpCode == -1" }
    frameHeader(
        streamId = 0,
        length = 8 + debugData.size,
        type = TYPE_GOAWAY,
        flags = FLAG_NONE
    )
    sink.writeInt(lastGoodStreamId)
    sink.writeInt(errorCode.httpCode)
    if (debugData.isNotEmpty()) {
      sink.write(debugData)
    }
    sink.flush()
  }

  /**
   * Inform peer that an additional `windowSizeIncrement` bytes can be sent on `streamId`, or the
   * connection if `streamId` is zero.
   */
  @Synchronized @Throws(IOException::class)
  fun windowUpdate(streamId: Int, windowSizeIncrement: Long) {
    if (closed) throw IOException("closed")
    require(windowSizeIncrement != 0L && windowSizeIncrement <= 0x7fffffffL) {
      "windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: $windowSizeIncrement"
    }
    frameHeader(
        streamId = streamId,
        length = 4,
        type = TYPE_WINDOW_UPDATE,
        flags = FLAG_NONE
    )
    sink.writeInt(windowSizeIncrement.toInt())
    sink.flush()
  }

  @Throws(IOException::class)
  fun frameHeader(streamId: Int, length: Int, type: Int, flags: Int) {
    if (logger.isLoggable(FINE)) logger.fine(frameLog(false, streamId, length, type, flags))
    require(length <= maxFrameSize) { "FRAME_SIZE_ERROR length > $maxFrameSize: $length" }
    require(streamId and 0x80000000.toInt() == 0) { "reserved bit set: $streamId" }
    sink.writeMedium(length)
    sink.writeByte(type and 0xff)
    sink.writeByte(flags and 0xff)
    sink.writeInt(streamId and 0x7fffffff)
  }

  @Synchronized @Throws(IOException::class)
  override fun close() {
    closed = true
    sink.close()
  }

  @Throws(IOException::class)
  private fun writeContinuationFrames(streamId: Int, byteCount: Long) {
    var byteCount = byteCount
    while (byteCount > 0L) {
      val length = minOf(maxFrameSize.toLong(), byteCount)
      byteCount -= length
      frameHeader(
          streamId = streamId,
          length = length.toInt(),
          type = TYPE_CONTINUATION,
          flags = if (byteCount == 0L) FLAG_END_HEADERS else 0
      )
      sink.write(hpackBuffer, length)
    }
  }

  @Synchronized @Throws(IOException::class)
  fun headers(
    outFinished: Boolean,
    streamId: Int,
    headerBlock: List<Header>
  ) {
    if (closed) throw IOException("closed")
    hpackWriter.writeHeaders(headerBlock)

    val byteCount = hpackBuffer.size
    val length = minOf(maxFrameSize.toLong(), byteCount)
    var flags = if (byteCount == length) FLAG_END_HEADERS else 0
    if (outFinished) flags = flags or FLAG_END_STREAM
    frameHeader(
        streamId = streamId,
        length = length.toInt(),
        type = TYPE_HEADERS,
        flags = flags
    )
    sink.write(hpackBuffer, length)

    if (byteCount > length) writeContinuationFrames(streamId, byteCount - length)
  }

  companion object {
    private val logger = Logger.getLogger(Http2::class.java.name)
  }
}
