package com.ruizurraca.marveltest.detail.presentation.models


class ThumbnailView private constructor(
    val path: String?,
    val extension: String?
) {
    data class Builder(
        var path: String? = null,
        var extension: String? = null
    ) {
        fun path(path: String) = apply { this.path = path }
        fun extension(extension: String) = apply { this.extension = extension }
        fun build() = ThumbnailView(path, extension)
    }
}

data class ComicsView(
    val available: Int?
) {
    data class Builder(
        var available: Int? = null
    ) {
        fun available(available: Int) = apply { this.available = available }
        fun build() = ComicsView(available)
    }
}

data class UrlView(
    val type: String?,
    val urlAddress: String?
) {
    data class Builder(
        var type: String? = null,
        var urlAddress: String? = null
    ) {
        fun type(type: String) = apply { this.type = type }
        fun urlAddress(urlAddress: String) = apply { this.urlAddress = urlAddress }
        fun build() = UrlView(type, urlAddress)
    }
}
