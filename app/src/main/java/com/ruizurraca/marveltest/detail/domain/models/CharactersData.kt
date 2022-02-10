package com.ruizurraca.marveltest.detail.domain.models

import com.ruizurraca.marveltest.detail.data.models.DataBaseDTO
import com.ruizurraca.marveltest.detail.presentation.models.CharactersView

class CharactersData private constructor(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val results: List<Character?>?,
    val total: Int?
) {
    companion object {
        @JvmStatic
        fun fromDTO(dataDTO: DataBaseDTO): CharactersData {
            val characters = mutableListOf<Character>()
            dataDTO.characters?.forEach { charDTO ->
                val characterBuilder = Character.Builder()
                charDTO?.id?.let { characterBuilder.id(it) }
                charDTO?.name?.let { characterBuilder.name(it) }
                charDTO?.description?.let { characterBuilder.description(it) }
                charDTO?.thumbnail?.let { characterBuilder.thumbnail(Thumbnail.fromDTO(it)) }
                charDTO?.comics?.let { characterBuilder.comics(Comics.fromDTO(it)) }
                charDTO?.urls?.let { urlList ->
                    characterBuilder.urls(urlList.map { Url.fromDTO(it) })
                }
                characters.add(characterBuilder.build())
            }
            val charactersDataBuilder = Builder()
            dataDTO.offset?.let { charactersDataBuilder.offset(it) }
            charactersDataBuilder.results(characters)
            dataDTO.total?.let { charactersDataBuilder.total(it) }
            return charactersDataBuilder.build()
        }

        @JvmStatic
        fun toView(charactersData: CharactersData): CharactersView {

            val characters = mutableListOf<CharactersView.CharacterView>()
            charactersData.results?.forEach { character ->
                val characterViewBuilder = CharactersView.CharacterView.Builder()
                character?.id?.let { characterViewBuilder.id(it) }
                character?.name?.let { characterViewBuilder.name(it) }
                character?.description?.let { characterViewBuilder.description(it) }
                character?.thumbnail?.let { characterViewBuilder.thumbnail(Thumbnail.toView(it)) }
                character?.comics?.let { characterViewBuilder.comics(Comics.toView(it)) }
                character?.urls?.let { urlList ->
                    characterViewBuilder.urls(urlList.map { Url.toView(it) })
                }
                characters.add(characterViewBuilder.build())
            }
            val charactersViewBuilder = CharactersView.Builder()
            charactersData.offset?.let { charactersViewBuilder.offset(it) }
            charactersViewBuilder.results(characters)
            charactersData.total?.let { charactersViewBuilder.total(it) }
            return charactersViewBuilder.build()
        }
    }

    data class Builder(
        var count: Int? = null,
        var limit: Int? = null,
        var offset: Int? = null,
        var results: List<Character?>? = null,
        var total: Int? = null
    ) {
        fun count(count: Int) = apply { this.count = count }
        fun limit(limit: Int) = apply { this.limit = limit }
        fun offset(offset: Int) = apply { this.offset = offset }
        fun results(results: List<Character?>) = apply { this.results = results }
        fun total(total: Int) = apply { this.total = total }
        fun build() = CharactersData(count, limit, offset, results, total)
    }

    class Character private constructor(
        val id: Int?,
        val name: String?,
        val description: String?,
        val thumbnail: Thumbnail?,
        val comics: Comics?,
        val urls: List<Url?>?
    ) {
        data class Builder(
            var id: Int? = null,
            var name: String? = null,
            var description: String? = null,
            var thumbnail: Thumbnail? = null,
            var comics: Comics? = null,
            var urls: List<Url?>? = listOf()
        ) {
            fun id(id: Int) = apply { this.id = id }
            fun name(name: String) = apply { this.name = name }
            fun description(description: String) = apply { this.description = description }
            fun thumbnail(thumbnail: Thumbnail) = apply { this.thumbnail = thumbnail }
            fun comics(comics: Comics) = apply { this.comics = comics }
            fun urls(urls: List<Url?>) = apply { this.urls = urls }
            fun build() = Character(id, name, description, thumbnail, comics, urls)
        }
    }
}
