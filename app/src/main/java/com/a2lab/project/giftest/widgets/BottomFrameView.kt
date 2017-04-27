package com.a2lab.project.giftest.widgets

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.a2lab.project.giftest.R
import com.a2lab.project.giftest.widgets.adapters.BottomViewAdapter
import kotlinx.android.synthetic.main.widget_bottom_view.view.*

/**
 * Created by pugman on 07.03.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
class BottomFrameView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var adapter: BottomViewAdapter

    init {
        LayoutInflater.from(this.context).inflate(R.layout.widget_bottom_view, this, true)
        bottomRecyclerView.setHasFixedSize(true)
        bottomRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        bottomRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    fun setItems(items: ArrayList<Int>, frame: (Int) -> Unit) {
        adapter = BottomViewAdapter(items) { frame(it) }
        bottomRecyclerView.adapter = adapter
    }

}