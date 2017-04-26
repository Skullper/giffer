package com.a2lab.project.giftest.utils

import android.app.Activity
import android.os.Environment
import android.util.Log
import com.a2lab.project.giftest.utils.REQUEST_CODES.REQUEST_CODE_CAMERA
import com.afollestad.materialcamera.MaterialCamera
import java.io.File

/**
 * Created by pugman on 27.02.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
class CameraUtil(val activity: Activity) {

    private val FOLDER_NAME = "GifferVideo"
    private val DELAY = 100L //between frames

    val saveFolder: File by lazy { File(Environment.getExternalStorageDirectory(), FOLDER_NAME) }

    init {
        //Exception can be ignored. The parent folder will be created anyway
        if (!saveFolder.mkdir())
            Log.e("FILE_CREATION", "failed create dir")
    }

    fun start(recordTime: Float) {
        val camera = MaterialCamera(activity)
        camera.apply {
            countdownSeconds(recordTime)
            autoSubmit(true)
            autoRecordWithDelayMs(DELAY)
            saveDir(saveFolder)
            defaultToFrontFacing(true)
            showPortraitWarning(false)
            qualityProfile(MaterialCamera.QUALITY_HIGH)
            start(REQUEST_CODE_CAMERA)
        }
    }

}