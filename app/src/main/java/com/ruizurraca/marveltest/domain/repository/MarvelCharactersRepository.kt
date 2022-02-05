package com.ruizurraca.marveltest.domain.repository

import com.ruizurraca.marveltest.domain.models.CharactersData
import com.ruizurraca.marveltest.domain.models.Result

interface MarvelCharactersRepository {
    suspend fun getCharacters(offset: Int? = null,
                              name: String? = null,
                              limit: Int? = null): Result<CharactersData>
}