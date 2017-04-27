package com.a2lab.project.giftest.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.a2lab.project.giftest.R
import kotlinx.android.synthetic.main.widget_round_button.view.*

/**
 * Created by pugman on 09.03.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
class RoundedButton @kotlin.jvm.JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(this.context).inflate(R.layout.widget_round_button, this, true)
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.RoundedButton)
        val text = attributes.getText(R.styleable.RoundedButton_title)
        val drawable = attributes.getDrawable(R.styleable.RoundedButton_image)
        this.widgetBut_text.text = text
        this.widgetBut_icon.setImageDrawable(drawable)
        attributes.recycle()
    }
}