package com.laioffer.source.okhttp3.internal.http

import com.laioffer.source.okhttp3.MediaType
import com.laioffer.source.okhttp3.MediaType.Companion.toMediaTypeOrNull
import com.laioffer.source.okhttp3.ResponseBody
import okio.BufferedSource

class RealResponseBody(
  /**
   * Use a string to avoid parsing the content type until needed. This also defers problems caused
   * by malformed content types.
   */
  private val contentTypeString: String?,
  private val contentLength: Long,
  private val source: BufferedSource
) : ResponseBody() {

  override fun contentLength(): Long = contentLength

  override fun contentType(): MediaType? = contentTypeString?.toMediaTypeOrNull()

  override fun source(): BufferedSource = source
}
