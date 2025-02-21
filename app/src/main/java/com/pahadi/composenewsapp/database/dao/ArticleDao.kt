package com.pahadi.composenewsapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.pahadi.composenewsapp.database.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(article: List<Article>)

    @Query("SELECT * FROM cached_articles")
    fun getAllArticles(): Flow<List<Article>>

    @Query("DELETE FROM cached_articles")
    fun deleteAll()

    @Transaction                // todo: usecase of @Transaction
    fun deleteAllAndInsertAll(articles: List<Article>){
        deleteAll()
        return insertAll(articles)
    }

}