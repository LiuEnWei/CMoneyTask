package com.wayne.cmoneytask.model

import android.util.Log
import com.wayne.cmoneytask.model.api.ApiService
import com.wayne.cmoneytask.model.api.vo.Photo

class Repository private constructor() {
    companion object {
        val instance: Repository by lazy { Repository() }
    }

    private val api: ApiService = ApiService.instance

    // Room Dao ...etc

    suspend fun getPhotos(): Array<Photo> {
        Log.e("Repository", "Repository : $instance")
        Log.e("Repository", "ApiService : $api")
        return api.getPhotos()
    }

}