package com.ruizurraca.marveltest.detail.data.api

import com.ruizurraca.marveltest.BuildConfig
import com.ruizurraca.marveltest.detail.data.models.CallData
import com.ruizurraca.marveltest.utils.md5

class MarvelApiCallGenerator {
    fun generateCall(charId: String): CallData {
        val publicApikey = BuildConfig.MARVEL_PUBLIC_KEY
        val privateApikey = BuildConfig.MARVEL_PRIVATE_KEY
        val timeStamp = System.currentTimeMillis()
        return CallData(
            publicApikey = publicApikey,
            timeStamp = timeStamp,
            hashSignature = "$timeStamp$privateApikey$publicApikey".md5(),
            charId = charId
        )
    }
}