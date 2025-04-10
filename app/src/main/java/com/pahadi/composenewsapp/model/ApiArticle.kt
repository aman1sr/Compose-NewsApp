package com.pahadi.composenewsapp.model

import com.google.gson.annotations.SerializedName

data class ApiArticle(

    @SerializedName("source")
    val source: Source,         // todo: update source Model later

    @SerializedName("author")
    val author: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("urlToImage")
    val urlToImage: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("content")
    val content: String?

)