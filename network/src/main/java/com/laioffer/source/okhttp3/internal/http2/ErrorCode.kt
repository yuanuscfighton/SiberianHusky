package com.laioffer.source.okhttp3.internal.http2

/** http://tools.ietf.org/html/draft-ietf-httpbis-http2-17#section-7 */
enum class ErrorCode constructor(val httpCode: Int) {
  /** Not an error!  */
  NO_ERROR(0),

  PROTOCOL_ERROR(1),

  INTERNAL_ERROR(2),

  FLOW_CONTROL_ERROR(3),

  SETTINGS_TIMEOUT(4),

  STREAM_CLOSED(5),

  FRAME_SIZE_ERROR(6),

  REFUSED_STREAM(7),

  CANCEL(8),

  COMPRESSION_ERROR(9),

  CONNECT_ERROR(0xa),

  ENHANCE_YOUR_CALM(0xb),

  INADEQUATE_SECURITY(0xc),

  HTTP_1_1_REQUIRED(0xd);

  companion object {
    fun fromHttp2(code: Int): ErrorCode? = values().find { it.httpCode == code }
  }
}
