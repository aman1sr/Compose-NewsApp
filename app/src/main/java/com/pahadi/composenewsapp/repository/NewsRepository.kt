package com.pahadi.composenewsapp.repository

import com.pahadi.composenewsapp.Util.Const.DEFAULT_PAGE_NUM
import com.pahadi.composenewsapp.common.apiArticleListToArticleList
import com.pahadi.composenewsapp.database.DatabaseService
import com.pahadi.composenewsapp.database.entity.Article
import com.pahadi.composenewsapp.network.ApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/*
* todo: https://developer.android.com/topic/architecture/data-layer/offline-first#exposing-resources
* */
@Singleton
class NewsRepository @Inject constructor(       // When you annotate a class with @Inject in its constructor, Hilt recognizes that it can provide instances of this class wherever it is needed in the dependency graph.
    private val network: ApiInterface,
    private val database: DatabaseService
) {
    suspend fun getNewsByCountry(
        countryCode: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> = flow {//todo: visualize flow builder
        emit(
            network.getNews(
                countryCode,
                pageNum = pageNumber
            ).articles.apiArticleListToArticleList()
        )
    }.flatMapConcat { articles ->
        flow {
            emit(database.deleteAllAndInsertAll(articles))
        }
    }.flatMapConcat {
        database.getAllArticles()
    }

}