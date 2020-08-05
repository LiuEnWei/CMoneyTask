package com.wayne.cmoneytask.model.api

import com.google.gson.Gson
import com.wayne.cmoneytask.Constant
import com.wayne.cmoneytask.model.Repository
import com.wayne.cmoneytask.model.api.vo.Photo
import com.wayne.cmoneytask.utils.NetworkUtils

class ApiService private constructor() {
    companion object {
        val instance: ApiService by lazy { ApiService() }
    }

    private val gson: Gson = Gson()

    suspend fun getPhotos(): Array<Photo> {
        val result = NetworkUtils.get(Constant.BASE_API_URL + Constant.API_PHOTOS)
        return gson.fromJson(result, Array<Photo>::class.java)
    }
}