package com.pahadi.composenewsapp.base

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.pahadi.composenewsapp.R

sealed class Route(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val icon: Int,
    val routeWithoutArg: String = route
) {
    object TopNews: Route("topNews/{country}/{language}/{source}", R.string.news,R.drawable.ic_news,"topNews")
    object FilterNews: Route("filterNews",R.string.filter,R.drawable.ic_filter)
    object SavedNews : Route("savedNews", R.string.saved, R.drawable.ic_save)
    object SearchNews : Route("searchNews", R.string.search, R.drawable.ic_search)
    object NewsArticle :
        Route("newsArticle/{article}", R.string.news, R.drawable.ic_news, "newsArticle")
}

val bottomBarScreens = listOf(
    Route.TopNews,
    Route.FilterNews,
    Route.SavedNews,
    Route.SearchNews
)