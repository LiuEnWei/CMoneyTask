package com.wayne.cmoneytask.model.api.vo

import java.io.Serializable

data class Photo(val albumId: Int,
                 val id: Int,
                 val title: String,
                 val url: String,
                 val thumbnailUrl: String): Serializable {

    override fun toString(): String {
        return "{ \"albumId\": $albumId, " +
                "\"id\": $id, " +
                "\"title\": $title, " +
                "\"url\": $url, " +
                "\"thumbnailUrl\": $thumbnailUrl }"
    }
}