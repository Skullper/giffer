package com.a2lab.project.giftest

import android.app.Application
import android.content.Context

/**
 * Created by pugman on 31.01.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
class ApplicationWrapper : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}