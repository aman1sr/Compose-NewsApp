package com.pahadi.composenewsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.pahadi.composenewsapp.Util.Const
import com.pahadi.composenewsapp.base.UIState
import com.pahadi.composenewsapp.common.dispatcher.DispatcherProvider
import com.pahadi.composenewsapp.common.networkhelper.NetworkHelper
import com.pahadi.composenewsapp.database.entity.Article
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import com.pahadi.composenewsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {

    private var searchPageNum = Const.DEFAULT_PAGE_NUM
    private val _searchNewsItem = MutableStateFlow<UIState<List<Article>>>(UIState.Empty)
    val searchNewsItem: StateFlow<UIState<List<Article>>> = _searchNewsItem
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

}