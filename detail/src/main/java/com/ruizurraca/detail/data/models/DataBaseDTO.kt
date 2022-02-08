package com.ruizurraca.detail.data.models

import com.google.gson.annotations.SerializedName

class DataBaseDTO(
    @SerializedName("results") val characters: List<CharacterDTO?>? = listOf(),
    val offset: Int? = null,
    val total: Int? = null
)