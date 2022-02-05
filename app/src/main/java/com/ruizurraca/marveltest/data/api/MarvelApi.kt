package com.ruizurraca.marveltest.data.api

import com.ruizurraca.marveltest.data.models.DataBaseDTO
import com.ruizurraca.marveltest.data.models.MarvelBaseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query(value = "apikey") apikey: String,
        @Query(value = "ts") ts: String,
        @Query(value = "hash") hash: String,
        @Query(value = "offset") offset: Int? = null,
        @Query(value = "nameStartsWith") name: String? = null,
        @Query(value = "limit") limit: Int? = null,
    ): Response<MarvelBaseDTO<DataBaseDTO>>
}