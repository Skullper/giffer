package com.a2lab.project.giftest.main.presentation

import com.a2lab.project.giftest.arch.presentation.BaseView

/**
 * Created by pugman on 28.02.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
interface MainView: BaseView{

    fun updateTimerView(seconds: String)

    fun onTimerFinished()

    fun setRecordTime(time: Float?)

}