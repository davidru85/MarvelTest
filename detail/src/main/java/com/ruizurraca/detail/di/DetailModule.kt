package com.ruizurraca.detail.di

import com.ruizurraca.detail.BuildConfig
import com.ruizurraca.detail.data.api.MarvelApiCallGenerator
import com.ruizurraca.detail.data.repository.MarvelCharacterDetailRepositoryImpl
import com.ruizurraca.detail.domain.repository.MarvelCharacterDetailRepository
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
import com.ruizurraca.detail.data.api.MarvelApi as MarvelApiDetail

@Module
@InstallIn(SingletonComponent::class)
object DetailModule {

    @Singleton
    @Provides
    @Named("interceptorDetail")
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    @Named("okHttpDetail")
    fun providesOkHttpClient(@Named("interceptorDetail") httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    @Named("retrofitDetail")
    fun provideRetrofit(@Named("okHttpDetail") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    @Named("marvelApiDetail")
    fun provideMarvelApiDetail(@Named("retrofitDetail") retrofit: Retrofit): MarvelApiDetail =
        retrofit.create(MarvelApiDetail::class.java)

    @Singleton
    @Provides
    @Named("marvelApiCallGeneratorDetail")
    fun provideMarvelApiCallGenerator(): MarvelApiCallGenerator = MarvelApiCallGenerator()

    @Singleton
    @Provides
    fun provideMarvelCharacterDetailRepository(
        @Named("marvelApiDetail") marvelApi: MarvelApiDetail,
        @Named("marvelApiCallGeneratorDetail") marvelApiCallGenerator: MarvelApiCallGenerator
    ): MarvelCharacterDetailRepository =
        MarvelCharacterDetailRepositoryImpl(marvelApi, marvelApiCallGenerator)
}