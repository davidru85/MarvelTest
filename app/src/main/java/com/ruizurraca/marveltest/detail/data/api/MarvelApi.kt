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
        @Path("id") id: String?
    ): Response<MarvelBaseDTO<DataBaseDTO>>
}