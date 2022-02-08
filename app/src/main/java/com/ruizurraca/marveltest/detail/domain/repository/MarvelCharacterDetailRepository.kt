package com.ruizurraca.marveltest.detail.domain.repository

import com.ruizurraca.marveltest.detail.domain.models.CharactersData
import com.ruizurraca.marveltest.detail.domain.models.Result

interface MarvelCharacterDetailRepository{
    suspend fun getCharacterDetail(charId: String? = null): Result<CharactersData>
}