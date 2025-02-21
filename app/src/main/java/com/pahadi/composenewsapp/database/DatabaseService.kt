package com.pahadi.composenewsapp.database

import com.pahadi.composenewsapp.database.entity.Article
import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    // saving news
    suspend fun upsert(article: Article)
    fun getSavedArticles(): Flow<List<Article>>     // todo: why not suspend

    //caching News
    fun getAllArticles(): Flow<List<Article>>
    fun deleteAllAndInsertAll(articles: List<Article>)       // todo: why not suspend, it doesn't even have Flow
}