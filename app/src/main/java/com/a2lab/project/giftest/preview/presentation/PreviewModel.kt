package com.a2lab.project.giftest.preview.presentation

import com.a2lab.project.giftest.api.createRetroFitService
import com.a2lab.project.giftest.api.responses.UploadingResponse
import com.a2lab.project.giftest.api.services.ApiService
import com.a2lab.project.giftest.arch.presentation.BaseModel
import okhttp3.RequestBody
import retrofit2.Call

/**
 * Created by pugman on 28.02.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
class PreviewModel : BaseModel {

    fun upload(file: RequestBody): Call<UploadingResponse> = createRetroFitService<ApiService>().uploadGif(file)
}