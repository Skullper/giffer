package com.a2lab.project.giftest.api

import com.a2lab.project.giftest.utils.END_POINT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by pugman on 26.03.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
inline fun <reified T> createRetroFitService(): T {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(END_POINT)
            .client(client)
            .build()
    return retrofit.create(T::class.java)
}
