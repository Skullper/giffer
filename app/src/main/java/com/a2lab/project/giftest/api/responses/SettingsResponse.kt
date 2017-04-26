package com.a2lab.project.giftest.api.responses

import com.google.gson.annotations.SerializedName

/**
* Created by pugman on 26.03.17.
* Contact the developer - sckalper@gmail.com
* company - A2Lab
*/
class SettingsResponse: BaseResponse() {

    @SerializedName("captureTime")
    lateinit var recordTime: String
}