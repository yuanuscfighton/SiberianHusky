package com.laioffer.协程

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by 春夏秋冬在中南 on 2023/5/3 22:50
 */
fun main() {
 GlobalScope.launch {
  delay(1000)
  println("Kotlin Coroutines")
 }
 println("Hello")
 Thread.sleep(2000)
 println("World")
}