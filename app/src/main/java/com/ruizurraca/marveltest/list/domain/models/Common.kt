package com.ruizurraca.marveltest.list.domain.models

import com.ruizurraca.marveltest.list.data.models.ComicsDTO
import com.ruizurraca.marveltest.list.data.models.ThumbnailDTO
import com.ruizurraca.marveltest.list.data.models.UrlDTO
import com.ruizurraca.marveltest.list.presentation.home.models.ComicsView
import com.ruizurraca.marveltest.list.presentation.home.models.ThumbnailView
import com.ruizurraca.marveltest.list.presentation.home.models.UrlView

class Thumbnail private constructor(
    val path: String?,
    var extension: String?
) {
    companion object {
        @JvmStatic
        fun fromDTO(thumbnailDTO: ThumbnailDTO?): Thumbnail {
            val thumbnailBuilder = Builder()
            thumbnailDTO?.path?.let { thumbnailBuilder.path(it) }
            thumbnailDTO?.extension?.let { thumbnailBuilder.extension(it) }
            return thumbnailBuilder.build()
        }

        @JvmStatic
        fun toView(thumbnail: Thumbnail?): ThumbnailView {
            val thumbnailViewBuilder = ThumbnailView.Builder()
            thumbnail?.path?.let { thumbnailViewBuilder.path(it) }
            thumbnail?.extension?.let { thumbnailViewBuilder.extension(it) }
            return thumbnailViewBuilder.build()
        }
    }

    data class Builder(
        var path: String? = null,
        var extension: String? = null
    ) {
        fun path(path: String) = apply { this.path = path }
        fun extension(extension: String) = apply { this.extension = extension }
        fun build() = Thumbnail(path, extension)
    }
}

class Comics private constructor(
    val available: Int?
) {
    companion object {
        @JvmStatic
        fun fromDTO(comicsDTO: ComicsDTO?): Comics {
            val comicsBuilder = Builder()
            comicsDTO?.available?.let { comicsBuilder.available(it) }
            return comicsBuilder.build()
        }

        @JvmStatic
        fun toView(comics: Comics?): ComicsView {
            val comicsViewBuilder = ComicsView.Builder()
            comics?.available?.let { comicsViewBuilder.available(it) }
            return comicsViewBuilder.build()
        }
    }

    data class Builder(
        var available: Int? = null
    ) {
        fun available(available: Int) = apply { this.available = available }
        fun build() = Comics(available)
    }
}

class Url private constructor(
    var type: String? = null,
    var urlAddress: String? = null
) {
    companion object {
        @JvmStatic
        fun fromDTO(urlDTO: UrlDTO?): Url {
            val urlBuilder = Builder()
            urlDTO?.type?.let { urlBuilder.type(it) }
            urlDTO?.urlAddress?.let { urlBuilder.urlAddress(it) }
            return urlBuilder.build()
        }

        @JvmStatic
        fun toView(url: Url?): UrlView {
            val urlViewBuilder = UrlView.Builder()
            url?.type?.let { urlViewBuilder.type(it) }
            url?.urlAddress?.let { urlViewBuilder.urlAddress(it) }
            return urlViewBuilder.build()
        }
    }

    data class Builder(
        var type: String? = null,
        var urlAddress: String? = null
    ) {
        fun type(type: String) = apply { this.type = type }
        fun urlAddress(urlAddress: String) = apply { this.urlAddress = urlAddress }
        fun build() = Url(type, urlAddress)
    }
}