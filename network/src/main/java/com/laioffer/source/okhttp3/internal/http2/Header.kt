package com.laioffer.source.okhttp3.internal.http2

import okio.ByteString
import okio.ByteString.Companion.encodeUtf8

/** HTTP header: the name is an ASCII string, but the value can be UTF-8. */
data class Header(
  /** Name in case-insensitive ASCII encoding. */
  @JvmField val name: ByteString,
  /** Value in UTF-8 encoding. */
  @JvmField val value: ByteString
) {
  @JvmField val hpackSize = 32 + name.size + value.size

  // TODO: search for toLowerCase and consider moving logic here.
  constructor(name: String, value: String) : this(name.encodeUtf8(), value.encodeUtf8())

  constructor(name: ByteString, value: String) : this(name, value.encodeUtf8())

  override fun toString(): String = "${name.utf8()}: ${value.utf8()}"

  companion object {
    // Special header names defined in HTTP/2 spec.
    @JvmField val PSEUDO_PREFIX: ByteString = ":".encodeUtf8()

    const val RESPONSE_STATUS_UTF8 = ":status"
    const val TARGET_METHOD_UTF8 = ":method"
    const val TARGET_PATH_UTF8 = ":path"
    const val TARGET_SCHEME_UTF8 = ":scheme"
    const val TARGET_AUTHORITY_UTF8 = ":authority"

    @JvmField val RESPONSE_STATUS: ByteString = RESPONSE_STATUS_UTF8.encodeUtf8()
    @JvmField val TARGET_METHOD: ByteString = TARGET_METHOD_UTF8.encodeUtf8()
    @JvmField val TARGET_PATH: ByteString = TARGET_PATH_UTF8.encodeUtf8()
    @JvmField val TARGET_SCHEME: ByteString = TARGET_SCHEME_UTF8.encodeUtf8()
    @JvmField val TARGET_AUTHORITY: ByteString = TARGET_AUTHORITY_UTF8.encodeUtf8()
  }
}
