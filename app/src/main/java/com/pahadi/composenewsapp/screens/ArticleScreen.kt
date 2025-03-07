package com.pahadi.composenewsapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.pahadi.composenewsapp.R
import com.pahadi.composenewsapp.base.ShowError
import com.pahadi.composenewsapp.base.WebViewPage
import com.pahadi.composenewsapp.database.entity.Article
import com.pahadi.composenewsapp.viewmodel.SharedViewModel
import androidx.compose.ui.tooling.preview.Preview
import com.pahadi.composenewsapp.database.entity.Source

@Composable
fun ArticleScreen(
    article: Article?,
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val mContext = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (article != null) {
//                    sharedViewModel.saveArticle()     //todo: make f()
                }
                Toast.makeText(mContext, mContext.resources.getString(R.string.article_saved_successfully), Toast.LENGTH_SHORT).show()
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_save), contentDescription = null )
            }
        }
    ){
        if (article?.url == null) {
            ShowError(text = stringResource(id = R.string.something_went_wrong))
        }else{
            WebViewPage(url = article.url,
                modifier = Modifier.padding(it)
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArticleScreen() {
    ArticleScreen(
        article = Article(
            id = 1, // Mock ID
            source = Source(id = "101", name = "Medium"), // Mock Source object
            author = "Aman Sharma", // Mock author
            title = "Network Calling in Android via Retrofit & Hilt", // Mock title
            description = "A comprehensive guide on network calling in Android.", // Mock description
            url = "https://medium.com/@aman1024/network-calling-in-android-via-retrofit-hilt-di-part-2-caa21afe2c39", // Provided URL
//          url = null,
            urlToImage = "https://example.com/image.jpg", // Mock image URL
            publishedAt = "2023-10-01T12:00:00Z", // Mock published date
            content = "This article discusses network calling in Android using Retrofit and Hilt." // Mock content
        ),
        sharedViewModel = hiltViewModel() // You may need to provide a mock or default ViewModel if necessary
    )
}