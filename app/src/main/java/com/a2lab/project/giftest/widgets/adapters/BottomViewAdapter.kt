package com.a2lab.project.giftest.widgets.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a2lab.project.giftest.R
import kotlinx.android.synthetic.main.item_bottom_view.view.*
import org.jetbrains.anko.imageResource

/**
 * Created by pugman on 07.03.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
class BottomViewAdapter(val items: ArrayList<Int>, val onFrameSelected: (Int) -> Unit) : RecyclerView.Adapter<BottomViewAdapter.BottomViewHolder>() {

    override fun onBindViewHolder(holder: BottomViewHolder?, position: Int) {
        holder?.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BottomViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_bottom_view, parent, false)
        return BottomViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    inner class BottomViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(frameResource: Int) {
            itemView.itemBotView_Image.imageResource = frameResource
            itemView.itemBotView_Image.setOnClickListener { onFrameSelected(frameResource) }
        }
    }
}