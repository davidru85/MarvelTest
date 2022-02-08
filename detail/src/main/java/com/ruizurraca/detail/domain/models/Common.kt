package com.ruizurraca.detail.domain.models

import com.ruizurraca.detail.data.models.ComicsDTO
import com.ruizurraca.detail.data.models.ThumbnailDTO
import com.ruizurraca.detail.data.models.UrlDTO
import com.ruizurraca.detail.presentation.models.ComicsView
import com.ruizurraca.detail.presentation.models.ThumbnailView
import com.ruizurraca.detail.presentation.models.UrlView

class Thumbnail(
    var path: String? = null,
    var extension: String? = null
) {
    companion object {
        @JvmStatic
        fun fromDTO(thumbnailDTO: ThumbnailDTO?): Thumbnail {
            return Thumbnail().apply {
                path = thumbnailDTO?.path
                extension = thumbnailDTO?.extension
            }
        }

        @JvmStatic
        fun toView(thumbnail: Thumbnail?): ThumbnailView {
            return ThumbnailView().apply {
                path = thumbnail?.path
                extension = thumbnail?.extension
            }
        }
    }
}

data class Comics(
    var available: Int? = null
) {
    companion object {
        @JvmStatic
        fun fromDTO(comicsDTO: ComicsDTO?): Comics {
            return Comics().apply {
                available = comicsDTO?.available
            }
        }

        @JvmStatic
        fun toView(comics: Comics?): ComicsView {
            return ComicsView().apply {
                available = comics?.available
            }
        }
    }
}

data class Url(
    var type: String? = null,
    var urlAddress: String? = null
) {
    companion object {
        @JvmStatic
        fun fromDTO(urlDTO: UrlDTO?): Url {
            return Url().apply {
                type = urlDTO?.type
                urlAddress = urlDTO?.urlAddress
            }
        }

        @JvmStatic
        fun toView(url: Url?): UrlView {
            return UrlView().apply {
                type = url?.type
                urlAddress = url?.urlAddress
            }
        }
    }
}