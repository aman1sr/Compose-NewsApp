package com.pahadi.composenewsapp.common

import com.pahadi.composenewsapp.database.entity.Article
import com.pahadi.composenewsapp.model.ApiArticle

fun ApiArticle.apiArticleToArticle(): Article{
    return Article(
    source = source,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}
fun List<ApiArticle>.apiArticleListToArticleList(): List<Article>{
    val list = mutableListOf<Article>()
    forEach { apiArticle ->
        list.add(apiArticle.apiArticleToArticle())
    }
    return list
}
