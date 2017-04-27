package com.a2lab.project.giftest.main.presentation

import com.a2lab.project.giftest.api.createRetroFitService
import com.a2lab.project.giftest.api.responses.SettingsResponse
import com.a2lab.project.giftest.api.services.ApiService
import com.a2lab.project.giftest.arch.presentation.BaseModel
import retrofit2.Call

/**
 * Created by pugman on 28.02.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
class MainModel : BaseModel {

    fun getResponse(): Call<SettingsResponse> = createRetroFitService<ApiService>().getCaptureTime()
}