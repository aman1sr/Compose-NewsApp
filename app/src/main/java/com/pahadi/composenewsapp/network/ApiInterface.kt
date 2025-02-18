package com.pahadi.composenewsapp.network

import com.pahadi.composenewsapp.Util.Const.DEFAULT_COUNTRY
import com.pahadi.composenewsapp.Util.Const.DEFAULT_PAGE_NUM
import com.pahadi.composenewsapp.Util.Const.DEFAULT_QUERY_PAGE_SIZE
import com.pahadi.composenewsapp.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = DEFAULT_COUNTRY,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News

}