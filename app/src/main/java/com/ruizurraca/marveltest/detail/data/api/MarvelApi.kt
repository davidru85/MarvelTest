package com.ruizurraca.marveltest.detail.data.api

import com.ruizurraca.marveltest.detail.data.models.DataBaseDTO
import com.ruizurraca.marveltest.detail.data.models.MarvelBaseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/characters/{id}")
    suspend fun getCharacterById(
        @Path("id") charId: String?,
        @Query(value = "apikey") apikey: String,
        @Query(value = "ts") ts: String,
        @Query(value = "hash") hash: String
    ): Response<MarvelBaseDTO<DataBaseDTO>>
}