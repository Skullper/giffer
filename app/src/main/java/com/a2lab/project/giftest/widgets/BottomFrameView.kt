package com.a2lab.project.giftest.widgets

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.a2lab.project.giftest.R
import com.a2lab.project.giftest.widgets.adapters.BottomViewAdapter

/**
 * Created by pugman on 07.03.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
class BottomFrameView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val recyclerView: RecyclerView
    private lateinit var adapter: BottomViewAdapter

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.widget_bottom_view, this, true)
        recyclerView = view.findViewById(R.id.bottomRecyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    fun setItems(items: ArrayList<Int>, frame: (Int) -> Unit) {
        adapter = BottomViewAdapter(items) { frame(it) }
        recyclerView.adapter = adapter
    }

}