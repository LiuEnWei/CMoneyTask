package com.wayne.cmoneytask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.wayne.cmoneytask.R
import com.wayne.cmoneytask.model.api.vo.Photo
import com.wayne.cmoneytask.model.imageloader.ImageLoader
import com.wayne.cmoneytask.ui.adapter.holder.PhotoViewHolder

class PhotosAdapter(var photos: Array<Photo> = arrayOf(),
                    val lifecycleScope: LifecycleCoroutineScope,
                    val onPhotoClickListener: OnPhotoClickListener): RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo, parent, false))
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val data = photos[position]
        holder.textId.text = data.id.toString()
        holder.textTitle.text = data.title
        holder.item.setOnClickListener {
            onPhotoClickListener.onClick(data)
        }
        ImageLoader.with(null).displayImage(data.thumbnailUrl, lifecycleScope, holder.imgThumbnailUrl)
    }
}