package com.pahadi.composenewsapp.database

import com.pahadi.composenewsapp.database.entity.Article
import kotlinx.coroutines.flow.Flow

class AppDatabaseService(
    private val articleDatabase: ArticleDatabase
): DatabaseService {
    override suspend fun upsert(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    override fun getAllArticles(): Flow<List<Article>> {
        return articleDatabase.getArticleDao().getAllArticles()
    }

    override fun deleteAllAndInsertAll(articles: List<Article>) {
        return articleDatabase.getArticleDao().deleteAllAndInsertAll(articles)
    }
}