package com.pahadi.composenewsapp.repository

import com.pahadi.composenewsapp.Util.Const.DEFAULT_PAGE_NUM
import com.pahadi.composenewsapp.model.ApiArticle
import com.pahadi.composenewsapp.network.ApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val network : ApiInterface
) {
    suspend fun getNewsByCountry(       // todo: update it wrt offline caching
        countryCode: String,
        pageNumber : Int = DEFAULT_PAGE_NUM
    ): Flow<List<ApiArticle>> = flow {
        emit(
            network.getNews(
                countryCode,
                pageNum = pageNumber
            ).articles
        )
    }

}