package com.laioffer.source.okhttp3

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.ISO_8859_1
import okio.ByteString.Companion.encode

/** Factory for HTTP authorization credentials. */
object Credentials {
  /** Returns an auth credential for the Basic scheme. */
  @JvmStatic @JvmOverloads fun basic(
    username: String,
    password: String,
    charset: Charset = ISO_8859_1
  ): String {
    val usernameAndPassword = "$username:$password"
    val encoded = usernameAndPassword.encode(charset).base64()
    return "Basic $encoded"
  }
}
