package com.ruizurraca.marveltest.list.data.models

class CharacterDTO(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val thumbnail: ThumbnailDTO? = null,
    val comics: ComicsDTO? = null,
    val urls: List<UrlDTO?>? = listOf()
)