package com.a2lab.project.giftest.extensions

import android.app.Activity
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import com.a2lab.project.giftest.utils.Resource
import com.a2lab.project.giftest.utils.SNACK_BAR_DURATION
import com.a2lab.project.giftest.utils.SimpleMessage
import com.a2lab.project.giftest.utils.Text
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by pugman on 10.05.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
//LOGS
fun <A: Any> A.log(text: String = "", throwable: Throwable? = null) = apply { Log.e(this.toString(), text, throwable) }

//VIEWS
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Activity.showSnack(message: SimpleMessage) = when (message) {
    is Text -> Snackbar.make(activity_container, message.asText, SNACK_BAR_DURATION).show()
    is Resource -> Snackbar.make(activity_container, message.asResourceId, SNACK_BAR_DURATION).show()
}