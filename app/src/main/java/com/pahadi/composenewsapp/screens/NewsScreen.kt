package com.pahadi.composenewsapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pahadi.composenewsapp.R
import com.pahadi.composenewsapp.base.ShowError
import com.pahadi.composenewsapp.base.ShowLoading
import com.pahadi.composenewsapp.base.UIState
import com.pahadi.composenewsapp.components.NewsLayout
import com.pahadi.composenewsapp.database.entity.Article
import com.pahadi.composenewsapp.viewmodel.NewsViewModel

@Composable
fun NewsScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
    modifier: Modifier
) {
    val newsUiState: UIState<List<Article>> by newsViewModel.newsItem.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (newsUiState) {
            is UIState.Loading -> {
                ShowLoading()
            }

            is UIState.Success -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val newsList = (newsUiState as UIState.Success<List<Article>>).data
                    val context = LocalContext.current
                    NewsLayout(newsList = newsList) {

                    }
                }
            }

            is UIState.Failure -> {
                var errorText = stringResource(id = R.string.something_went_wrong)
                ShowError(
                    text = errorText,
                    retryEnable = true
                ) {
                    newsViewModel.fetchNewsbyCountry("us")
                }
            }

            else -> {

            }
        }
    }
}