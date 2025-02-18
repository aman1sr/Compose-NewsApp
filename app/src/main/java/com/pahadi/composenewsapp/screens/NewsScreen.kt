package com.pahadi.composenewsapp.screens

import android.icu.lang.UCharacter.VerticalOrientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pahadi.composenewsapp.base.ShowLoading
import com.pahadi.composenewsapp.base.UIState
import com.pahadi.composenewsapp.model.ApiArticle
import com.pahadi.composenewsapp.viewmodel.NewsViewModel

@Composable
fun NewsScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
    modifier: Modifier
) {
    val newsUiState: UIState<List<ApiArticle>> by newsViewModel.newsItem.collectAsStateWithLifecycle()
    
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when(newsUiState){
            is UIState.Loading -> {
                ShowLoading()
            }
            is UIState.Success -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val newsCount = (newsUiState as UIState.Success<List<ApiArticle>>).data.size
                    Text(
                        text = "received ${newsCount} News",
                        fontSize = 30.sp
                    )
                }
            }
          else -> {
              
          }
        }
    }
}