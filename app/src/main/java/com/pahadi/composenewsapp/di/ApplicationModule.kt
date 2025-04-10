package com.pahadi.composenewsapp.di

import com.pahadi.composenewsapp.Util.Const
import com.pahadi.composenewsapp.network.ApiInterface
import com.pahadi.composenewsapp.network.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = Const.BASE_URL

    @ApiKey
    @Provides
    fun provideApiKey(): String =
        "65270e8438dd46d2bc787a822da0cb72"        // todo: add via BuildConfig

    @Singleton
    @Provides
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonFactory: GsonConverterFactory,
        apiKeyInterceptor: ApiKeyInterceptor
    ): ApiInterface {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()

        return Retrofit
            .Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(gsonFactory)
            .build()
            .create(ApiInterface::class.java)
    }

}