package com.pahadi.composenewsapp.viewmodel.filter

import androidx.lifecycle.ViewModel
import com.pahadi.composenewsapp.base.UIState
import com.pahadi.composenewsapp.common.dispatcher.DispatcherProvider
import com.pahadi.composenewsapp.common.networkhelper.NetworkHelper
import com.pahadi.composenewsapp.database.entity.Source
import com.pahadi.composenewsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SourceFilterViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _sourceItem = MutableStateFlow<UIState<List<Source>>>(UIState.Empty)
    val sourceItem: StateFlow<UIState<List<Source>>> = _sourceItem

}