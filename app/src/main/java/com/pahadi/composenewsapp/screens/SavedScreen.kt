package com.pahadi.composenewsapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.pahadi.composenewsapp.R
import com.pahadi.composenewsapp.base.ShowError
import com.pahadi.composenewsapp.database.entity.Article
import com.pahadi.composenewsapp.viewmodel.SharedViewModel

@Composable
fun SavedScreen(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    newsClicked : (Article) -> Unit
) {
//    val newsList: List<Article> by sharedViewModel.getSavedNews()
//        .collectAsStateWithLifecycle(emptyList())
//
//    if (newsList.isEmpty()) {
//        ShowError(text = stringResource(R.string.no_saved_news))
//    } else {
//        NewsLayoutWithDelete(newsList = newsList,
//            articleClicked = {
//                newsClicked(it)
//            }) {
//            sharedViewModel.deleteArticle(it)
//        }
//    }
}