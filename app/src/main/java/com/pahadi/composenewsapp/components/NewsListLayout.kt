package com.pahadi.composenewsapp.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pahadi.composenewsapp.database.entity.Article
import com.pahadi.composenewsapp.database.entity.Source

@Composable
fun NewsLayout(
    newsList: List<Article>,
    articleClicked: (Article) -> Unit
) {
    LazyColumn {
        items(newsList) {
            Article(article = it) { article ->
                articleClicked(article)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsLayoutWithDelete(
    newsList: List<Article>,
    articleClicked: (Article) -> Unit,
    deleteArticle: (Article) -> Unit
) {
    LazyColumn {
        items(newsList, key = { article -> article.url!! }) { article ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(direction = DismissDirection.EndToStart) ||
                dismissState.isDismissed(direction = DismissDirection.StartToEnd)
            ) {
                deleteArticle(article)
            }
            SwipeToDismiss(state = dismissState, background = {},
                dismissContent = {
                    com.pahadi.composenewsapp.components.Article(article = article) {
                        articleClicked(it)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsLayoutWithDelete() {
    val sampleArticles = listOf(
        Article(
            author = "Author 1",
            title = "Title 1",
            description = "Description 1",
            url = "https://news.example.com/article1",
            urlToImage = "https://media.contentapi.ea.com/content/dam/ufc/ufc-5/homepage/images/2024/06/ufc5ue-hero-lg-homepage-tophero-xl.jpg.adapt.crop1x1.320w.jpg",
            publishedAt = "2023-10-10T10:00:00Z",
            content = "Content 1",
            source = Source(id = "source1", name = "Source 1")
        ),
        Article(
            author = "Author 2",
            title = "Title 2",
            description = "Description 2",
            url = "https://news.example.com/article2",
            urlToImage = "https://bloodyelbow.com/wp-content/uploads/1/2024/11/GettyImages-1248061337-scaled.jpg",
            publishedAt = "2023-10-10T10:00:00Z",
            content = "Content 2",
            source = Source(id = "source2", name = "Source 2")
        ),
        Article(
            author = "Author 3",
            title = "Title 3",
            description = "Description 3",
            url = "https://news.example.com/article3",
            urlToImage = "https://e00-xlk-ue-marca.uecdn.es/uploads/2024/12/12/675b1a0922822.jpeg",
            publishedAt = "2023-10-10T10:00:00Z",
            content = "Content 3",
            source = Source(id = "source3", name = "Source 3")
        )
    )

    NewsLayoutWithDelete(
        newsList = sampleArticles,
        articleClicked = { /* Handle article click */ },
        deleteArticle = { /* Handle article deletion */ }
    )
}