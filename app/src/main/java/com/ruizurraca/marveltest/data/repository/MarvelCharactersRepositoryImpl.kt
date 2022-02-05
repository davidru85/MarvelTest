package com.ruizurraca.marveltest.data.repository

import android.util.Log
import com.ruizurraca.marveltest.BuildConfig
import com.ruizurraca.marveltest.data.api.MarvelApi
import com.ruizurraca.marveltest.data.models.DataBaseDTO
import com.ruizurraca.marveltest.domain.models.CharactersData
import com.ruizurraca.marveltest.domain.models.Result
import com.ruizurraca.marveltest.domain.repository.MarvelCharactersRepository
import com.ruizurraca.marveltest.utils.md5
import retrofit2.HttpException
import javax.inject.Inject

class MarvelCharactersRepositoryImpl @Inject constructor(private val marvelApi: MarvelApi) :
    MarvelCharactersRepository, BaseRepository() {

    companion object {
        private val TAG = MarvelCharactersRepositoryImpl::class.java.name
        const val STATE_ERROR = 1
        const val STATE_LOADING = 2
    }

    override suspend fun getCharacters(
        offset: Int?,
        name: String?,
        limit: Int?
    ): Result<CharactersData> {

        var result: Result<CharactersData> = handleSuccess(CharactersData())

        try {
            val publicApikey = BuildConfig.MARVEL_PUBLIC_KEY
            val privateApikey = BuildConfig.MARVEL_PRIVATE_KEY
            val timeStamp = System.currentTimeMillis()
            val hashSignature = "$timeStamp$privateApikey$publicApikey".md5()

            val response = marvelApi.getCharacters(
                apikey = publicApikey,
                ts = timeStamp.toString(),
                hash = hashSignature,
                offset = offset,
                name = name,
                limit = limit
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
        return result
    }
}