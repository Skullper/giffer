package com.a2lab.project.giftest.extensions

import android.util.Log

/**
 * Created by pugman on 31.03.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
fun <A: Any> A.log(text: String = "", throwable: Throwable? = null) = apply { Log.e(this.toString(), text, throwable) }