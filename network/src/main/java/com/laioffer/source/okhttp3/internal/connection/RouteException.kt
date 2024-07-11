package com.laioffer.source.okhttp3.internal.connection

import java.io.IOException

/**
 * An exception thrown to indicate a problem connecting via a single Route. Multiple attempts may
 * have been made with alternative protocols, none of which were successful.
 */
class RouteException internal constructor(val firstConnectException: IOException) :
    RuntimeException(firstConnectException) {
  var lastConnectException: IOException = firstConnectException
    private set

  fun addConnectException(e: IOException) {
    firstConnectException.addSuppressed(e)
    lastConnectException = e
  }
}
