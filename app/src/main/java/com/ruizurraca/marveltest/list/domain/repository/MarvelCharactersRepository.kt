package com.ruizurraca.marveltest.list.domain.repository

import com.ruizurraca.marveltest.list.domain.models.CharactersData
import com.ruizurraca.marveltest.list.domain.models.Result

interface MarvelCharactersRepository {
    suspend fun getCharacters(offset: Int? = null,
                              name: String? = null,
                              limit: Int? = null): Result<CharactersData>
}