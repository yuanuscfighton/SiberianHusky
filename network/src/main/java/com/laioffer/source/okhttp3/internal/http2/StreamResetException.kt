package com.laioffer.source.okhttp3.internal.http2

import java.io.IOException

/** Thrown when an HTTP/2 stream is canceled without damage to the socket that carries it. */
class StreamResetException(@JvmField val errorCode: ErrorCode) : IOException("stream was reset: $errorCode")
