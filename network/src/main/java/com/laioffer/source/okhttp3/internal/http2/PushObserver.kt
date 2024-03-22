package com.laioffer.source.okhttp3.internal.http2

import java.io.IOException
import com.laioffer.source.okhttp3.Protocol
import okio.BufferedSource

/**
 * [HTTP/2][Protocol.HTTP_2] only. Processes server-initiated HTTP requests on the client.
 * Implementations must quickly dispatch callbacks to avoid creating a bottleneck.
 *
 * While [onReset] may occur at any time, the following callbacks are expected in order,
 * correlated by stream ID.
 *
 *  * [onRequest]
 *  * [onHeaders] (unless canceled)
 *  * [onData] (optional sequence of data frames)
 *
 * As a stream ID is scoped to a single HTTP/2 connection, implementations which target multiple
 * connections should expect repetition of stream IDs.
 *
 * Return true to request cancellation of a pushed stream.  Note that this does not guarantee
 * future frames won't arrive on the stream ID.
 */
interface PushObserver {
  /**
   * Describes the request that the server intends to push a response for.
   *
   * @param streamId server-initiated stream ID: an even number.
   * @param requestHeaders minimally includes `:method`, `:scheme`, `:authority`,
   * and `:path`.
   */
  fun onRequest(streamId: Int, requestHeaders: List<Header>): Boolean

  /**
   * The response headers corresponding to a pushed request.  When [last] is true, there are
   * no data frames to follow.
   *
   * @param streamId server-initiated stream ID: an even number.
   * @param responseHeaders minimally includes `:status`.
   * @param last when true, there is no response data.
   */
  fun onHeaders(streamId: Int, responseHeaders: List<Header>, last: Boolean): Boolean

  /**
   * A chunk of response data corresponding to a pushed request.  This data must either be read or
   * skipped.
   *
   * @param streamId server-initiated stream ID: an even number.
   * @param source location of data corresponding with this stream ID.
   * @param byteCount number of bytes to read or skip from the source.
   * @param last when true, there are no data frames to follow.
   */
  @Throws(IOException::class)
  fun onData(streamId: Int, source: BufferedSource, byteCount: Int, last: Boolean): Boolean

  /** Indicates the reason why this stream was canceled. */
  fun onReset(streamId: Int, errorCode: ErrorCode)

  companion object {
    @JvmField val CANCEL: PushObserver = PushObserverCancel()
    private class PushObserverCancel : PushObserver {

      override fun onRequest(streamId: Int, requestHeaders: List<Header>): Boolean {
        return true
      }

      override fun onHeaders(streamId: Int, responseHeaders: List<Header>, last: Boolean): Boolean {
        return true
      }

      @Throws(IOException::class)
      override fun onData(streamId: Int, source: BufferedSource, byteCount: Int, last: Boolean): Boolean {
        source.skip(byteCount.toLong())
        return true
      }

      override fun onReset(streamId: Int, errorCode: ErrorCode) {
      }
    }
  }
}
