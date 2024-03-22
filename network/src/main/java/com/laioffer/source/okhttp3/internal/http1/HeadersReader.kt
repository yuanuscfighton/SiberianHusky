package com.laioffer.source.okhttp3.internal.http1

import com.laioffer.source.okhttp3.Headers
import okio.BufferedSource

/**
 * Parse all headers delimited by "\r\n" until an empty line. This throws if headers exceed 256 KiB.
 */
class HeadersReader(val source: BufferedSource) {
  private var headerLimit = HEADER_LIMIT.toLong()

  /** Read a single line counted against the header size limit. */
  fun readLine(): String {
    val line = source.readUtf8LineStrict(headerLimit)
    headerLimit -= line.length.toLong()
    return line
  }

  /** Reads headers or trailers. */
  fun readHeaders(): Headers {
    val result = Headers.Builder()
    while (true) {
      val line = readLine()
      if (line.isEmpty()) break
      result.addLenient(line)
    }
    return result.build()
  }

  companion object {
    private const val HEADER_LIMIT = 256 * 1024
  }
}
