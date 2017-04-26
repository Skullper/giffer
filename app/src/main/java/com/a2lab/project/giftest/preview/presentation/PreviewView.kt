package com.a2lab.project.giftest.preview.presentation

import android.graphics.Bitmap
import android.graphics.drawable.AnimationDrawable
import com.a2lab.project.giftest.arch.presentation.BaseView

/**
 * Created by pugman on 28.02.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
interface PreviewView: BaseView {

    fun onFrameObtainError(message: Any)

    fun setPreview(bitmap: Bitmap)

    fun provideGif(animation: AnimationDrawable)

    fun openShareAct(url: String)

}