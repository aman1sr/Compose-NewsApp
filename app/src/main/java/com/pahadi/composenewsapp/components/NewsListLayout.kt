package com.pahadi.composenewsapp.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.pahadi.composenewsapp.database.entity.Article

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