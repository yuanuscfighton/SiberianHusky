package com.laioffer.source.okhttp3.internal.platform.android

import android.util.Log
import com.laioffer.source.okhttp3.internal.concurrent.TaskRunner
import com.laioffer.source.okhttp3.internal.platform.android.AndroidLog.androidLog
import com.laioffer.source.okhttp3.OkHttpClient
import com.laioffer.source.okhttp3.internal.SuppressSignatureCheck
import com.laioffer.source.okhttp3.internal.http2.Http2
import java.util.concurrent.CopyOnWriteArraySet
import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger

private val LogRecord.androidLevel: Int
  get() = when {
    level.intValue() > Level.INFO.intValue() -> Log.WARN
    level.intValue() == Level.INFO.intValue() -> Log.INFO
    else -> Log.DEBUG
  }

object AndroidLogHandler : Handler() {
  override fun publish(record: LogRecord) {
    androidLog(record.loggerName, record.androidLevel, record.message, record.thrown)
  }

  override fun flush() {
  }

  override fun close() {
  }
}

@SuppressSignatureCheck
object AndroidLog {
  private const val MAX_LOG_LENGTH = 4000

  // Keep references to loggers to prevent their configuration from being GC'd.
  private val configuredLoggers = CopyOnWriteArraySet<Logger>()

  private val knownLoggers = LinkedHashMap<String, String>().apply {
    val packageName = OkHttpClient::class.java.`package`?.name

    if (packageName != null) {
      this[packageName] = "OkHttp"
    }

    this[OkHttpClient::class.java.name] = "okhttp.OkHttpClient"
    this[Http2::class.java.name] = "okhttp.Http2"
    this[TaskRunner::class.java.name] = "okhttp.TaskRunner"
    this["okhttp3.mockwebserver.MockWebServer"] = "okhttp.MockWebServer"
  }.toMap()

  internal fun androidLog(loggerName: String, logLevel: Int, message: String, t: Throwable?) {
    val tag = loggerTag(loggerName)

    if (Log.isLoggable(tag, logLevel)) {
      var logMessage = message
      if (t != null) logMessage = logMessage + '\n'.toString() + Log.getStackTraceString(t)

      // Split by line, then ensure each line can fit into Log's maximum length.
      var i = 0
      val length = logMessage.length
      while (i < length) {
        var newline = logMessage.indexOf('\n', i)
        newline = if (newline != -1) newline else length
        do {
          val end = minOf(newline, i + MAX_LOG_LENGTH)
          Log.println(logLevel, tag, logMessage.substring(i, end))
          i = end
        } while (i < newline)
        i++
      }
    }
  }

  private fun loggerTag(loggerName: String): String {
    // We need to handle long logger names before they hit Log.
    // java.lang.IllegalArgumentException: Log tag "okhttp3.mockwebserver.MockWebServer" exceeds limit of 23 characters
    return knownLoggers[loggerName] ?: loggerName.take(23)
  }

  fun enable() {
    for ((logger, tag) in knownLoggers) {
      enableLogging(logger, tag)
    }
  }

  private fun enableLogging(logger: String, tag: String) {
    val logger = Logger.getLogger(logger)
    if (configuredLoggers.add(logger)) {
      logger.useParentHandlers = false
      // log based on levels at startup to avoid logging each frame
      logger.level = when {
        Log.isLoggable(tag, Log.DEBUG) -> Level.FINE
        Log.isLoggable(tag, Log.INFO) -> Level.INFO
        else -> Level.WARNING
      }
      logger.addHandler(AndroidLogHandler)
    }
  }
}
