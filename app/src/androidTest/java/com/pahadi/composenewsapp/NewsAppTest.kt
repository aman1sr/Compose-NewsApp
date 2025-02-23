package com.pahadi.composenewsapp

import com.pahadi.composenewsapp.model.ApiArticle
import com.pahadi.composenewsapp.model.News
import com.pahadi.composenewsapp.model.Source
import com.pahadi.composenewsapp.network.ApiInterface
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.junit.MockitoJUnitRunner

//@RunWith(MockitoJUnitRunner::class)
class NewsAppTest {

//    @Mock
    private lateinit var apiInterface: ApiInterface

    @Test
    fun getNews_whenNetworkServiceResponseSuccess_shouldReturnSuccess() = runTest {
        // Create test data
        val source = Source(id = "sourceId", name = "sourceName")
        val article = ApiArticle(
            source = source,
            title = "title",
            description = "description",
            url = "url",
            urlToImage = "urlToImage",
            author = "author",
            content = "content",
            publishedAt = "pat"
        )

        val articles = listOf(article)
        // Rename the variable to avoid conflicts.
        val mockNewsResponse = News(
            status = "ok",
            totalResults = 1,
            articles = articles
        )

        // Configure Mockito to return the mocked response when getNews("us") is called.
//        Mockito.`when`(apiInterface.getNews("us"))
//            .thenReturn(mockNewsResponse)
//
//        // Act: Call the API interface.
//        val response = apiInterface.getNews("us")
//
//        // Assert: Validate that the response status is "ok".
//        assertEquals("ok", response.status)
    }
}
