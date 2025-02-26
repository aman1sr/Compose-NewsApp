package com.pahadi.composenewsapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.pahadi.composenewsapp.Util.Const
import com.pahadi.composenewsapp.common.dispatcher.DefaultDispatcherProvider
import com.pahadi.composenewsapp.common.dispatcher.DispatcherProvider
import com.pahadi.composenewsapp.common.logger.AppLogger
import com.pahadi.composenewsapp.common.logger.Logger
import com.pahadi.composenewsapp.common.networkhelper.NetworkHelper
import com.pahadi.composenewsapp.common.networkhelper.NetworkHelperImpl
import com.pahadi.composenewsapp.database.AppDatabaseService
import com.pahadi.composenewsapp.database.ArticleDatabase
import com.pahadi.composenewsapp.database.DatabaseService
import com.pahadi.composenewsapp.network.ApiInterface
import com.pahadi.composenewsapp.network.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideLogger():Logger = AppLogger()

    @Provides
    @Singleton
    fun provideNetworkHelper(
        @ApplicationContext context: Context
    ): NetworkHelper{
        return NetworkHelperImpl(context)
    }

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
    @DbName
    @Provides
    fun provideDbName(): String = Const.DB_NAME

    @Singleton
    @Provides
    fun provideArticleDatabase(
        application: Application,
        @DbName dbName: String
    ): ArticleDatabase{
        return Room.databaseBuilder(
            application,
            ArticleDatabase::class.java,
            dbName
        )
            .build()
    }


    @Singleton
    @Provides
    fun provideDatabaseService(articleDatabase: ArticleDatabase): DatabaseService{
        return AppDatabaseService(articleDatabase)
    }

}