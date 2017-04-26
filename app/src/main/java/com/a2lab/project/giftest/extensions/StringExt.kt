package com.a2lab.project.giftest.extensions

import android.util.Log

/**
 * Created by pugman on 31.03.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
fun String.log(text: String? = "Empty", throwable: Throwable?){
    Log.e(this, text, throwable)
}

fun String.log(text: String? = "Empty"){
    Log.e(this, text)
}



