package com.laioffer.source.okhttp3.internal.http

import java.io.IOException
import com.laioffer.source.okhttp3.Headers
import com.laioffer.source.okhttp3.Request
import com.laioffer.source.okhttp3.Response
import com.laioffer.source.okhttp3.internal.connection.RealConnection
import okio.Sink
import okio.Source

/** Encodes HTTP requests and decodes HTTP responses. */
interface ExchangeCodec {
  /** Returns the connection that carries this codec. */
  val connection: RealConnection

  /** Returns an output stream where the request body can be streamed. */
  @Throws(IOException::class)
  fun createRequestBody(request: Request, contentLength: Long): Sink

  /** This should update the HTTP engine's sentRequestMillis field. */
  @Throws(IOException::class)
  fun writeRequestHeaders(request: Request)

  /** Flush the request to the underlying socket. */
  @Throws(IOException::class)
  fun flushRequest()

  /** Flush the request to the underlying socket and signal no more bytes will be transmitted. */
  @Throws(IOException::class)
  fun finishRequest()

  /**
   * Parses bytes of a response header from an HTTP transport.
   *
   * @param expectContinue true to return null if this is an intermediate response with a "100"
   * response code. Otherwise this method never returns null.
   */
  @Throws(IOException::class)
  fun readResponseHeaders(expectContinue: Boolean): Response.Builder?

  @Throws(IOException::class)
  fun reportedContentLength(response: Response): Long

  @Throws(IOException::class)
  fun openResponseBodySource(response: Response): Source

  /** Returns the trailers after the HTTP response. May be empty. */
  @Throws(IOException::class)
  fun trailers(): Headers

  /**
   * Cancel this stream. Resources held by this stream will be cleaned up, though not synchronously.
   * That may happen later by the connection pool thread.
   */
  fun cancel()

  companion object {
    /**
     * The timeout to use while discarding a stream of input data. Since this is used for connection
     * reuse, this timeout should be significantly less than the time it takes to establish a new
     * connection.
     */
    const val DISCARD_STREAM_TIMEOUT_MILLIS = 100
  }
}
