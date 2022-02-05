package com.ruizurraca.marveltest.data.models

class MarvelBaseDTO<T>(
    var code: Int,
    var status: String,
    var data: T?
)