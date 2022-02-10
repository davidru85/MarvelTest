package com.ruizurraca.detail.presentation.models

class CharactersView private constructor(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val results: List<CharacterView?>?,
    val total: Int?
) {

    data class Builder(
        var count: Int? = null,
        var limit: Int? = null,
        var offset: Int? = null,
        var results: List<CharacterView?>? = null,
        var total: Int? = null
    ) {
        fun count(count: Int) = apply { this.count = count }
        fun limit(limit: Int) = apply { this.limit = limit }
        fun offset(offset: Int) = apply { this.offset = offset }
        fun results(results: List<CharacterView?>) = apply { this.results = results }
        fun total(total: Int) = apply { this.total = total }
        fun build() = CharactersView(count, limit, offset, results, total)
    }

    class CharacterView private constructor(
        val id: Int?,
        val name: String?,
        val description: String?,
        val thumbnail: ThumbnailView?,
        val comics: ComicsView?,
        val urls: List<UrlView?>?
    ) {
        fun getComicsUrl(): List<String?>? = urls?.map { it?.urlAddress }

        data class Builder(
            var id: Int? = null,
            var name: String? = null,
            var description: String? = null,
            var thumbnail: ThumbnailView? = null,
            var comics: ComicsView? = null,
            var urls: List<UrlView?>? = listOf()
        ) {
            fun id(id: Int) = apply { this.id = id }
            fun name(name: String) = apply { this.name = name }
            fun description(description: String) = apply { this.description = description }
            fun thumbnail(thumbnail: ThumbnailView) = apply { this.thumbnail = thumbnail }
            fun comics(comics: ComicsView) = apply { this.comics = comics }
            fun urls(urls: List<UrlView?>) = apply { this.urls = urls }
            fun build() = CharacterView(id, name, description, thumbnail, comics, urls)
        }
    }
}
