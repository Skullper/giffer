package com.a2lab.project.giftest.api

import com.a2lab.project.giftest.utils.Constantaz
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by pugman on 26.03.17.
 */
fun <T> createRetroFitService(clazz: Class<T>): T {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constantaz.END_POINT)
            .client(client)
            .build()
    return retrofit.create(clazz)
}
