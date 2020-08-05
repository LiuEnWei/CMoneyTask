package com.wayne.cmoneytask.ui.adapter

import com.wayne.cmoneytask.model.api.vo.Photo

interface OnPhotoClickListener {
    fun onClick(photo: Photo)
}