package com.a2lab.project.giftest.arch.presentation

import com.a2lab.project.giftest.utils.SimpleMessage

/**
 * Created by pugman on 06.12.16.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
interface BaseView {

    fun showMessage(message: SimpleMessage)

    fun showProgress()

    fun hideProgress()

}
