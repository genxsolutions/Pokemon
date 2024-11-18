package com.genxsol.pokemon.data.repository.di

import com.genxsol.pokemon.data.repository.BaseUrl
import com.genxsol.pokemon.data.repository.Constants
import com.genxsol.pokemon.data.repository.api.PokemonApi
import com.genxsol.pokemon.data.repository.repository.PokemonRepositoryImpl
import com.genxsol.pokemon.domain.repository.PokemonRepository
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.Module
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = Constants.BASE_URL

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun providePokemonApi(
        @BaseUrl baseUrl: String,
        httpClient: OkHttpClient
    ): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }
}