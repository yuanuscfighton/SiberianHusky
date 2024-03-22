package com.laioffer.source.okhttp3.internal.http2

import java.io.IOException

/**
 * Thrown when an HTTP/2 connection is shutdown (either explicitly or if the peer has sent a GOAWAY
 * frame) and an attempt is made to use the connection.
 */
class ConnectionShutdownException : IOException()
