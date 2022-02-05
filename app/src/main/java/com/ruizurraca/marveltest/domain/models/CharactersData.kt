package com.ruizurraca.marveltest.domain.models

import com.ruizurraca.marveltest.data.models.DataBaseDTO
import com.ruizurraca.marveltest.presentation.home.models.CharactersView
import com.ruizurraca.marveltest.presentation.home.models.ComicsView
import com.ruizurraca.marveltest.presentation.home.models.ThumbnailView

data class CharactersData(
    var count: Int? = null,
    var limit: Int? = null,
    var offset: Int? = null,
    var results: List<Character?>? = null,
    var total: Int? = null
) {
    companion object {
        @JvmStatic
        fun fromDTO(dataDTO: DataBaseDTO): CharactersData {
            val characters = mutableListOf<Character>()
            dataDTO.characters?.forEach {
                characters.add(Character().apply {
                    id = it?.id
                    name = it?.name
                    description = it?.description
                    thumbnail = Thumbnail.fromDTO(it?.thumbnail)
                    comics = Comics.fromDTO(it?.comics)
                    urls = it?.urls?.map { Url.fromDTO(it) }
                })
            }
            return CharactersData().apply {
                offset = dataDTO.offset
                results = characters
                total = dataDTO.total
            }
        }

        @JvmStatic
        fun toView(charactersData: CharactersData): CharactersView {
            val charactersView = mutableListOf<CharactersView.CharacterView>()
            charactersData.results?.forEach {
                charactersView.add(CharactersView.CharacterView().apply {
                    id = it?.id
                    name = it?.name
                    description = it?.description
                    thumbnail = Thumbnail.toView(it?.thumbnail)
                    comics = Comics.toView(it?.comics)
                    urls = it?.urls?.map { Url.toView(it) }
                })
            }
            return CharactersView().apply {
                offset = charactersData.offset
                results = charactersView
                total = charactersData.total
            }
        }
    }

    class Character(
        var id: Int? = null,
        var name: String? = null,
        var description: String? = null,
        var thumbnail: Thumbnail? = null,
        var comics: Comics? = null,
        var urls: List<Url?>? = listOf()
    )
}
