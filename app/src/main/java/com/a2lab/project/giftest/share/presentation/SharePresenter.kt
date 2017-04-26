package com.a2lab.project.giftest.share.presentation

import com.a2lab.project.giftest.arch.presentation.BasePresenter
import com.a2lab.project.giftest.arch.presentation.BaseView

/**
 * Created by pugman on 03.04.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

class SharePresenter(override val view: BaseView) : BasePresenter() {

    val model = ShareModel()
}