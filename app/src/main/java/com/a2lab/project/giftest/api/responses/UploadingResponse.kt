package com.a2lab.project.giftest.api.responses

import com.google.gson.annotations.SerializedName

/**
 * Created by pugman on 31.03.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

data class UploadingResponse(@SerializedName("file_url") val url: String): BaseResponse()