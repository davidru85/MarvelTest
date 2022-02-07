package com.ruizurraca.marveltest.list.data.models

class MarvelBaseDTO<T>(
    var code: Int,
    var status: String,
    var data: T?
)