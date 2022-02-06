package com.ruizurraca.marveltest.data.repository

import com.ruizurraca.marveltest.data.api.MarvelApi
import com.ruizurraca.marveltest.data.api.MarvelApiCallGenerator
import com.ruizurraca.marveltest.data.models.CallData
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MarvelCharactersRepositoryImplTest {

    @MockK
    var marvelApi: MarvelApi = mockk(relaxed = true)

    @MockK
    var marvelApiCallGenerator: MarvelApiCallGenerator = mockk(relaxed = true)

    lateinit var marvelCharactersRepository: MarvelCharactersRepositoryImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        marvelCharactersRepository = buildRepository()
    }

    @Test
    fun `repo getCharacters should call network datasource getCharacters`() {
        val callData = CallData(
            "apikey",
            12,
            "signature",
            23,
            "name",
            34
        )
        every { marvelApiCallGenerator.generateCall() } returns callData

        val response = runBlocking { marvelCharactersRepository.getCharacters() }

        coVerify {
            marvelApi.getCharacters(
                callData.publicApikey,
                "${callData.timeStamp}",
                callData.hashSignature,
                callData.offset,
                callData.name,
                callData.limit
            )
        }
    }

    private fun buildRepository() = MarvelCharactersRepositoryImpl(marvelApi, marvelApiCallGenerator)
}