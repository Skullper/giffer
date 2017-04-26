package com.a2lab.project.giftest.api.services

import com.a2lab.project.giftest.api.responses.SettingsResponse
import com.a2lab.project.giftest.api.responses.UploadingResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
* Created by pugman on 26.03.17.
* Contact the developer - sckalper@gmail.com
* company - A2Lab
*/
interface ApiService {

    @GET("settings/captureTime")
    fun getCaptureTime(): Call<SettingsResponse>

    @Headers("Accept: application/json")
    @POST("upload")
    @Multipart
    fun uploadGif(@Part("file\"; filename=\"pp.png") driverPhoto: RequestBody): Call<UploadingResponse>
}