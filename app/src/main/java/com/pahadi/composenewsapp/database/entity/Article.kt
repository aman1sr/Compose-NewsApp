package com.pahadi.composenewsapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cached_articles",
    indices = [Index(                       // indices parameter is used to create database indices on specified columns
        value = ["title", "url"],           //  Indexing speeds up query operations (like searches) on these columns.
        unique = true
    )]
)

data class Article(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Int = 0,

    @Embedded                    // @Embedded annotation is used when you want to include the fields of another object
    val source: Source,

    @ColumnInfo("author")
    val author: String?,

    @ColumnInfo("title")
    val title: String?,

    @ColumnInfo("description")
    val description: String?,

    @ColumnInfo("url")
    val url: String?,

    @ColumnInfo("urlToImage")
    val urlToImage: String?,

    @ColumnInfo("publishedAt")
    val publishedAt: String?,

    @ColumnInfo("content")
    val content: String?

)