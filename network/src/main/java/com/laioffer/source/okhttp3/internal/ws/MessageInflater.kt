package com.laioffer.source.okhttp3.internal.ws

import java.io.Closeable
import java.io.IOException
import java.util.zip.Inflater
import okio.Buffer
import okio.InflaterSource

private const val OCTETS_TO_ADD_BEFORE_INFLATION = 0x0000ffff

class MessageInflater(
  private val noContextTakeover: Boolean
) : Closeable {
  private val deflatedBytes = Buffer()
  private val inflater = Inflater(true /* omit zlib header */)
  private val inflaterSource = InflaterSource(deflatedBytes, inflater)

  /** Inflates [buffer] in place as described in RFC 7692 section 7.2.2. */
  @Throws(IOException::class)
  fun inflate(buffer: Buffer) {
    require(deflatedBytes.size == 0L)

    if (noContextTakeover) {
      inflater.reset()
    }

    deflatedBytes.writeAll(buffer)
    deflatedBytes.writeInt(OCTETS_TO_ADD_BEFORE_INFLATION)

    val totalBytesToRead = inflater.bytesRead + deflatedBytes.size

    // We cannot read all, as the source does not close.
    // Instead, we ensure that all bytes from source have been processed by inflater.
    do {
      inflaterSource.readOrInflate(buffer, Long.MAX_VALUE)
    } while (inflater.bytesRead < totalBytesToRead)
  }

  @Throws(IOException::class)
  override fun close() = inflaterSource.close()
}
