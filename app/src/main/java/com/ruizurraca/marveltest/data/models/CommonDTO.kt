package com.ruizurraca.marveltest.data.models

import com.google.gson.annotations.SerializedName

class ThumbnailDTO(
    var path: String? = null,
    var extension: String? = null
)

data class ComicsDTO(
    val available: Int? = null
)

data class UrlDTO(
    val type: String? = null,
    @SerializedName("url")
    val urlAddress: String? = null
)