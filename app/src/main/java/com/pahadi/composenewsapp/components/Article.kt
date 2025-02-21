package com.pahadi.composenewsapp.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pahadi.composenewsapp.database.entity.Article
import com.pahadi.composenewsapp.database.entity.Source

@Composable
fun Article(article: Article, onItemclick: (Article) -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp)
            .clickable {
                onItemclick(article)
                    Toast.makeText(context, "${article.title} clicked", Toast.LENGTH_SHORT).show()
            }
    ) {
        Row(modifier = Modifier.height(120.dp)) {
            article.urlToImage?.let {
                ArticleImage(urlToImage = it, title = article.title!!)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                article.title?.let {
                    ArticleTitle(title = it)
                }
                article.description?.let {
                    ArticleDescription(description = it)
                }

            }
        }
    }
}

@Composable
fun ArticleImage(urlToImage: String, title: String) {
    AsyncImage(
        model = urlToImage,
        contentDescription = title,
        contentScale = ContentScale.Crop,
        modifier = Modifier.width(150.dp)
    )
}

@Composable
fun ArticleTitle(title: String) {
    Text(
        text = title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun ArticleDescription(description: String) {
    Text(
        text = description,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.titleSmall
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewArticle() {
    val sampleArticle = Article(
        author = "John Doe",
        title = "Breaking News: Compose is Awesome!",
        description = "Compose simplifies and accelerates UI development on Android.",
        url = "https://news.example.com/article",
        urlToImage = "https://images.twinkl.co.uk/tr/image/upload/t_illustration/illustation/swimming-1.png",
        publishedAt = "2023-10-10T10:00:00Z",
        content = "This is the detailed content of the article. It provides more information than the description.",
        source = Source(id = "example", name = "Example Source")
    )
    Article(article = sampleArticle, onItemclick = {})
}