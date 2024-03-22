package com.laioffer.source.okhttp3.internal.cache

import java.io.IOException
import okio.Sink

interface CacheRequest {
  @Throws(IOException::class)
  fun body(): Sink

  fun abort()
}
