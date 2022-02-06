package com.ruizurraca.marveltest.data.models

class CallData(
    val publicApikey: String,
    val timeStamp: Long,
    val hashSignature: String,
    val offset: Int?,
    val name: String?,
    val limit: Int?
)