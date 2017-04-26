package com.a2lab.project.giftest.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.a2lab.project.giftest.R

/**
 * Created by pugman on 09.03.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
class RoundedButton @kotlin.jvm.JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val title: TextView
    val icon: ImageView

    init {
        val root = LayoutInflater.from(this.context).inflate(R.layout.widget_round_button, this, true)
        title = root.findViewById(R.id.widgetBut_text) as TextView
        icon = root.findViewById(R.id.widgetBut_icon) as ImageView
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.RoundedButton)
        val text = attributes.getText(R.styleable.RoundedButton_title)
        val drawable = attributes.getDrawable(R.styleable.RoundedButton_image)
        title.text = text
        icon.setImageDrawable(drawable)
        attributes.recycle()
    }
}