package com.ruizurraca.marveltest.detail.data.repository

import android.util.Log
import com.ruizurraca.marveltest.detail.domain.repository.MarvelCharacterDetailRepository
import com.ruizurraca.marveltest.detail.data.api.MarvelApi
import com.ruizurraca.marveltest.detail.data.models.DataBaseDTO
import com.ruizurraca.marveltest.detail.domain.models.CharactersData
import com.ruizurraca.marveltest.detail.domain.models.Result
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class MarvelCharacterDetailRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi
) :
    MarvelCharacterDetailRepository, BaseRepository() {

    companion object {
        private val TAG = MarvelCharacterDetailRepositoryImpl::class.java.name
        const val STATE_ERROR = 1
        const val STATE_LOADING = 2
    }

    override suspend fun getCharacterDetail(charId: String?): Result<CharactersData>{
        var result: Result<CharactersData> = handleSuccess(CharactersData())
        try{
            val response = marvelApi.getCharacterById(charId)

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
        }
        catch (error: HttpException) {
            Log.e(TAG, "Error: ${error.message}")
            return handleException(error.code())
        } catch (error: Exception) {
            Log.e(TAG, "Error: ${error.message}")
        }
        return result
    }
}