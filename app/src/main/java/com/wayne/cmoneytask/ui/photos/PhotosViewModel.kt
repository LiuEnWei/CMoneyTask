package com.wayne.cmoneytask.ui.photos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wayne.cmoneytask.model.Repository
import com.wayne.cmoneytask.model.api.vo.Photo
import com.wayne.cmoneytask.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class PhotosViewModel: BaseViewModel() {
    private val api: Repository = Repository.instance
    val photos = MutableLiveData<Array<Photo>>()

    fun getPhotos() {
        viewModelScope.launch {
            val result = api.getPhotos()
            Log.e("HomeViewModel", "${result.size}")
            photos.value = result
//            for (photo in result) {
//                Log.e("HomeViewModel", "$photo")
//            }
        }
    }
}