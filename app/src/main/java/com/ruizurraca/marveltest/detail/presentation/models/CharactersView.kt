package com.ruizurraca.marveltest.detail.presentation.models

data class CharactersView(
    var count: Int? = null,
    var limit: Int? = null,
    var offset: Int? = null,
    var results: List<CharacterView?>? = null,
    var total: Int? = null
) {
    class CharacterView(
        var id: Int? = null,
        var name: String? = null,
        var description: String? = null,
        var thumbnail: ThumbnailView? = null,
        var comics: ComicsView? = null,
        var urls: List<UrlView?>? = listOf()
    )
}