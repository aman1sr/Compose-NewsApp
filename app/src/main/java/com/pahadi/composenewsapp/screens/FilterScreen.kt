package com.pahadi.composenewsapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pahadi.composenewsapp.R
import com.pahadi.composenewsapp.base.ShowError
import com.pahadi.composenewsapp.base.ShowLoading
import com.pahadi.composenewsapp.base.UIState
import com.pahadi.composenewsapp.common.NoInternetException
import com.pahadi.composenewsapp.components.CountryListLayout
import com.pahadi.composenewsapp.components.LanguageListLayout
import com.pahadi.composenewsapp.components.SourceListLayout
import com.pahadi.composenewsapp.database.entity.Source
import com.pahadi.composenewsapp.model.Country
import com.pahadi.composenewsapp.model.Language
import com.pahadi.composenewsapp.viewmodel.filter.CountryFilterViewModel
import com.pahadi.composenewsapp.viewmodel.filter.LanguageFilterViewModel
import com.pahadi.composenewsapp.viewmodel.filter.SourceFilterViewModel
import java.lang.Exception

@Composable
fun CountryScreen(
    countryFilterViewModel: CountryFilterViewModel = hiltViewModel(),
    countryClicked: (Country) -> Unit
) {
    val countryUiState: UIState<List<Country>> by countryFilterViewModel.countryItem.collectAsStateWithLifecycle()

    when (countryUiState) {
        is UIState.Loading -> {
            ShowLoading()
        }

        is UIState.Failure -> {
            var errorText = stringResource(id = R.string.something_went_wrong)
            if((countryUiState as UIState.Failure<List<Country>>).throwable is Exception){
                errorText = stringResource(id = R.string.no_internet_available)
            }
            ShowError(text = errorText, retryEnable = true){
//                countryFilterViewModel.getCountries()     // todo: update f()
            }
        }

        is UIState.Success -> {
            CountryListLayout(countryList = (countryUiState as UIState.Success<List<Country>>).data) {
                countryClicked(it)
            }
        }

        is UIState.Empty -> {

        }
    }
}


@Composable
fun LanguageScreen(
    languageFilterViewModel: LanguageFilterViewModel = hiltViewModel(),
    languageClicked: (Language) -> Unit
) {
    val languageUiState: UIState<List<Language>> by languageFilterViewModel.languageItem.collectAsStateWithLifecycle()

    when (languageUiState) {
        is UIState.Loading -> {
            ShowLoading()
        }

        is UIState.Failure -> {
            var errorText = stringResource(id = R.string.something_went_wrong)
            if ((languageUiState as UIState.Failure<List<Language>>).throwable is NoInternetException) {
                errorText = stringResource(id = R.string.no_internet_available)
            }
            ShowError(
                text = errorText,
                retryEnable = true
            ) {
//                languageFilterViewModel.getLanguage()     // todo: add VM f()
            }
        }

        is UIState.Success -> {
            LanguageListLayout(languageList = (languageUiState as UIState.Success<List<Language>>).data) {
                languageClicked(it)
            }
        }

        is UIState.Empty -> {

        }
    }

}

@Composable
fun SourceScreen(
    sourceFilterViewModel: SourceFilterViewModel = hiltViewModel(),
    sourceClicked: (Source) -> Unit
) {
    val sourceUiState: UIState<List<Source>> by sourceFilterViewModel.sourceItem.collectAsStateWithLifecycle()

    when (sourceUiState) {
        is UIState.Loading -> {
            ShowLoading()
        }

        is UIState.Failure -> {
            var errorText = stringResource(id = R.string.something_went_wrong)
            if ((sourceUiState as UIState.Failure<List<Source>>).throwable is NoInternetException) {
                errorText = stringResource(id = R.string.no_internet_available)
            }
            ShowError(
                text = errorText,
                retryEnable = true
            ) {
//                sourceFilterViewModel.getSources()    // todo: add VM f()
            }
        }

        is UIState.Success -> {
            SourceListLayout(sourceList = (sourceUiState as UIState.Success<List<Source>>).data) {
                sourceClicked(it)
            }
        }

        is UIState.Empty -> {

        }
    }
}