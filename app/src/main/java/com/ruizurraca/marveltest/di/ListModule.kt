package com.ruizurraca.marveltest.di

import com.ruizurraca.marveltest.BuildConfig
import com.ruizurraca.marveltest.list.data.api.MarvelApiCallGenerator
import com.ruizurraca.marveltest.list.data.repository.MarvelCharactersRepositoryImpl
import com.ruizurraca.marveltest.list.domain.repository.MarvelCharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton
import com.ruizurraca.marveltest.list.data.api.MarvelApi as MarvelApiList

@Module
@InstallIn(SingletonComponent::class)
object ListModule {

    @Singleton
    @Provides
    @Named("interceptorList")
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    @Named("okHttpList")
    fun providesOkHttpClient(@Named("interceptorList") httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    @Named("retrofitList")
    fun provideRetrofit(@Named("okHttpList") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    @Named("marvelApiList")
    fun provideMarvelApiList(@Named("retrofitList") retrofit: Retrofit): MarvelApiList =
        retrofit.create(MarvelApiList::class.java)

    @Singleton
    @Provides
    @Named("marvelApiCallGeneratorList")
    fun provideMarvelApiCallGenerator(): MarvelApiCallGenerator = MarvelApiCallGenerator()

    @Singleton
    @Provides
    fun provideMarvelCharactersRepository(
        @Named("marvelApiList") marvelApi: MarvelApiList,
        @Named("marvelApiCallGeneratorList") marvelApiCallGenerator: MarvelApiCallGenerator
    ): MarvelCharactersRepository =
        MarvelCharactersRepositoryImpl(marvelApi, marvelApiCallGenerator)
}