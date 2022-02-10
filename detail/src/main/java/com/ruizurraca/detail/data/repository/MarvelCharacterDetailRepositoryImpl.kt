package com.ruizurraca.detail.data.repository

import android.util.Log
import com.ruizurraca.detail.data.api.MarvelApi
import com.ruizurraca.detail.domain.models.Result
import com.ruizurraca.detail.data.api.MarvelApiCallGenerator
import com.ruizurraca.detail.data.models.DataBaseDTO
import com.ruizurraca.detail.domain.models.CharactersData
import com.ruizurraca.detail.domain.repository.MarvelCharacterDetailRepository
import retrofit2.HttpException
import javax.inject.Inject

class MarvelCharacterDetailRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi,
    private val marvelApiCallGenerator: MarvelApiCallGenerator
) :
    MarvelCharacterDetailRepository, BaseRepository() {

    companion object {
        private val TAG = MarvelCharacterDetailRepositoryImpl::class.java.name
        const val STATE_ERROR = 1
        const val STATE_LOADING = 2
    }

    override suspend fun getCharacterDetail(charId: String?): Result<CharactersData> {
        var result: Result<CharactersData> = handleSuccess(CharactersData.Builder().build())

        charId?.let { characterId ->
            try {
                val callData = marvelApiCallGenerator.generateCall(characterId)

                val response = marvelApi.getCharacterById(
                    apikey = callData.publicApikey,
                    ts = "${callData.timeStamp}",
                    hash = callData.hashSignature,
                    charId = callData.charId
                )

                response.let {
                    it.body()?.data?.let { data: DataBaseDTO ->
                        result = handleSuccess(CharactersData.fromDTO(data))
                    }
                    it.errorBody()?.let { responseErrorBody ->
                        if (responseErrorBody is HttpException) {
                            responseErrorBody.response()?.code()?.let { errorCode ->
                                result = handleException(errorCode)
                            }
                        } else {
                            result = handleException(STATE_ERROR)
                        }
                    }
                }
            } catch (error: HttpException) {
                Log.e(TAG, "Error: ${error.message}")
                return handleException(error.code())
            } catch (error: Exception) {
                Log.e(TAG, "Error: ${error.message}")
            }
        }
        return result
    }
}