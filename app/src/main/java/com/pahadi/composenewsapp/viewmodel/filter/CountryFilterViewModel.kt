package com.pahadi.composenewsapp.viewmodel.filter

import androidx.lifecycle.ViewModel
import com.pahadi.composenewsapp.base.UIState
import com.pahadi.composenewsapp.common.dispatcher.DispatcherProvider
import com.pahadi.composenewsapp.common.networkhelper.NetworkHelper
import com.pahadi.composenewsapp.model.Country
import com.pahadi.composenewsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CountryFilterViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _countryItem = MutableStateFlow<UIState<List<Country>>>(UIState.Empty)
    val countryItem: StateFlow<UIState<List<Country>>> = _countryItem

}