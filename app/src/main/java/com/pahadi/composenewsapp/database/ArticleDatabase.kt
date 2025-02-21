package com.pahadi.composenewsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pahadi.composenewsapp.database.dao.ArticleDao
import com.pahadi.composenewsapp.database.entity.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
}