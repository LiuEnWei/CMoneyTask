package com.wayne.cmoneytask.ui.adapter.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wayne.cmoneytask.R

class PhotoViewHolder(val item: View): RecyclerView.ViewHolder(item) {
    val imgThumbnailUrl = item.findViewById<ImageView>(R.id.imgThumbnailUrl)
    val textId = item.findViewById<TextView>(R.id.textId)
    val textTitle = item.findViewById<TextView>(R.id.textTitle)
}