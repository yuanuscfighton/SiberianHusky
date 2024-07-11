package com.laioffer.source.okhttp3.internal.ws

import java.io.Closeable
import java.io.IOException
import java.util.zip.Deflater
import okio.Buffer
import okio.ByteString
import okio.ByteString.Companion.decodeHex
import okio.DeflaterSink

private val EMPTY_DEFLATE_BLOCK = "000000ffff".decodeHex()
private const val LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION = 4

class MessageDeflater(
  private val noContextTakeover: Boolean
) : Closeable {
  private val deflatedBytes = Buffer()
  private val deflater = Deflater(Deflater.DEFAULT_COMPRESSION, true /* omit zlib header */)
  private val deflaterSink = DeflaterSink(deflatedBytes, deflater)

  /** Deflates [buffer] in place as described in RFC 7692 section 7.2.1. */
  @Throws(IOException::class)
  fun deflate(buffer: Buffer) {
    require(deflatedBytes.size == 0L)

    if (noContextTakeover) {
      deflater.reset()
    }

    deflaterSink.write(buffer, buffer.size)
    deflaterSink.flush()

    if (deflatedBytes.endsWith(EMPTY_DEFLATE_BLOCK)) {
      val newSize = deflatedBytes.size - LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION
      deflatedBytes.readAndWriteUnsafe().use { cursor ->
        cursor.resizeBuffer(newSize)
      }
    } else {
      // Same as adding EMPTY_DEFLATE_BLOCK and then removing 4 bytes.
      deflatedBytes.writeByte(0x00)
    }

    buffer.write(deflatedBytes, deflatedBytes.size)
  }

  @Throws(IOException::class)
  override fun close() = deflaterSink.close()

  private fun Buffer.endsWith(suffix: ByteString) = rangeEquals(size - suffix.size, suffix)
}
