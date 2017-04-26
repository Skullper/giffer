package com.a2lab.project.giftest.main.presentation

import android.os.Handler
import com.a2lab.project.giftest.api.responses.SettingsResponse
import com.a2lab.project.giftest.arch.presentation.BasePresenter
import com.a2lab.project.giftest.extensions.log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pugman on 28.02.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
class MainPresenter(override val view: MainView) : BasePresenter() {

    private val model = MainModel()

    private val COUNTDOWN_TIME = 4
    private val TIMER_STEP_MILLIS = 1000L

    private var startTime = 0L
    private val handler: Handler by lazy { Handler() }

    fun startTimer() {
        startTime = System.currentTimeMillis()
        handler.removeCallbacks(mUpdateTimeTask)
        handler.post(mUpdateTimeTask)
    }

    private val mUpdateTimeTask = object : Runnable {
        override fun run() {
            val start = startTime
            val millis = System.currentTimeMillis() - start
            var seconds = (millis / 1000).toInt()
            seconds %= 60
            seconds = COUNTDOWN_TIME - seconds
            view.updateTimerView(seconds.toString())
            if (seconds == 0) {
                view.onTimerFinished()
                return
            }
            handler.postDelayed(this, TIMER_STEP_MILLIS)
        }
    }

    fun getRecordTime() {
        model.getResponse().enqueue(object : Callback<SettingsResponse> {
            override fun onFailure(call: Call<SettingsResponse>?, t: Throwable?) {
                "SETTINGS_FAILURE".log(throwable = t)
            }

            override fun onResponse(call: Call<SettingsResponse>?, response: Response<SettingsResponse>?) {
                if (response?.isSuccessful ?: false)
                    view.setRecordTime(response?.body()?.recordTime?.toFloat())
            }
        })
    }

}