package com.ruizurraca.detail.presentation.models

class ThumbnailView(
    var path: String? = null,
    var extension: String? = null
)

data class ComicsView(
    var available: Int? = null
)

data class UrlView(
    var type: String? = null,
    var urlAddress: String? = null
)