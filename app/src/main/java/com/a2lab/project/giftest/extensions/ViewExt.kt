package com.a2lab.project.giftest.extensions

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.View
import com.a2lab.project.giftest.utils.Constantaz
import com.a2lab.project.giftest.utils.Resource
import com.a2lab.project.giftest.utils.SimpleMessage
import com.a2lab.project.giftest.utils.Text
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by pugman on 27.04.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Activity.showSnack(message: SimpleMessage) = when (message) {
    is Text -> Snackbar.make(activity_container, message.asText, Constantaz.SNACK_BAR_DURATION).show()
    is Resource -> Snackbar.make(activity_container, message.asResourceId, Constantaz.SNACK_BAR_DURATION).show()
}