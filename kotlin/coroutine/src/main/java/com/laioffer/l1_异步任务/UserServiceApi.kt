package com.laioffer.l1_异步任务

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

data class HotWordResponse(
  val data: List<HotWordData>,
)

data class HotWordData(
  val id: Int,
  val name: String,
  val order: Int,
)

val userServiceApi: UserServiceApi by lazy {
  val retrofit = Retrofit.Builder()
    .client(OkHttpClient.Builder().addInterceptor {
      it.proceed(it.request()).apply {
        Log.e("TAG", "request:${code}")
      }
    }.build())
    .baseUrl("https://www.wanandroid.com/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
  retrofit.create(UserServiceApi::class.java)
}


interface UserServiceApi {
  @GET("/hotkey/json")
  fun requestHotWord(): Call<HotWordResponse>

  @GET("/hotkey/json")
  suspend fun getHotWordData(): HotWordResponse
}