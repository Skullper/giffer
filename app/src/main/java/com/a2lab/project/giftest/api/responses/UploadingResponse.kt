package com.a2lab.project.giftest.api.responses

import com.google.gson.annotations.SerializedName

/**
 * Created by pugman on 31.03.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

class UploadingResponse {

    @SerializedName("file_url")
    lateinit var url: String
}