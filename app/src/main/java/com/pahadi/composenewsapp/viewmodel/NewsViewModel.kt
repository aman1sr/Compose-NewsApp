package com.pahadi.composenewsapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pahadi.composenewsapp.Util.Const
import com.pahadi.composenewsapp.base.UIState
import com.pahadi.composenewsapp.database.entity.Article
import com.pahadi.composenewsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private var pageNum = Const.DEFAULT_PAGE_NUM
    private val _newsItem = MutableStateFlow<UIState<List<Article>>>(UIState.Empty)
    val newsItem: StateFlow<UIState<List<Article>>> = _newsItem

    init {
        fetchNewsbyCountry("us")
    }

     fun fetchNewsbyCountry(countryId: String?) {
        viewModelScope.launch {
            _newsItem.emit(UIState.Loading)
            val response = newsRepository.getNewsByCountry(
                countryId ?: Const.DEFAULT_COUNTRY,
                pageNumber = pageNum
            )
            response.collect {
                _newsItem.emit(UIState.Success(it))
                Log.d("News_d", "fetchNewsbyCountry: ${it}")
            }

        }
    }


}