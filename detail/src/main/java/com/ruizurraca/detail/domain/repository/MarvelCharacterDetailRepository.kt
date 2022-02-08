package com.ruizurraca.detail.domain.repository

import com.ruizurraca.detail.domain.models.CharactersData
import com.ruizurraca.detail.domain.models.Result

interface MarvelCharacterDetailRepository{
    suspend fun getCharacterDetail(charId: String? = null): Result<CharactersData>
}