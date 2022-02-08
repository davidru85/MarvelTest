package com.ruizurraca.marveltest.detail.data.repository

import com.ruizurraca.marveltest.detail.data.api.MarvelApi
import com.ruizurraca.marveltest.detail.data.api.MarvelApiCallGenerator
import com.ruizurraca.marveltest.detail.data.models.CallData
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MarvelCharacterDetailRepositoryImplTest {
    @MockK
    var marvelApi: MarvelApi = mockk(relaxed = true)

    @MockK
    var marvelApiCallGenerator: MarvelApiCallGenerator = mockk(relaxed = true)

    private lateinit var marvelCharacterDetailRepository: MarvelCharacterDetailRepositoryImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        marvelCharacterDetailRepository = buildRepository()
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "2", "3", "45"])
    fun `repo getCharacterDetail should call network datasource getCharacterDetail`(charId: String) {
        val callData = CallData(
            "apikey",
            12,
            "signature",
            charId
        )

        every { marvelApiCallGenerator.generateCall(charId) } returns callData

        val response = runBlocking { marvelCharacterDetailRepository.getCharacterDetail(charId) }

        coVerify {
            marvelApi.getCharacterById(
                callData.charId,
                callData.publicApikey,
                "${callData.timeStamp}",
                callData.hashSignature
            )
        }
    }

    private fun buildRepository() =
        MarvelCharacterDetailRepositoryImpl(marvelApi, marvelApiCallGenerator)
}