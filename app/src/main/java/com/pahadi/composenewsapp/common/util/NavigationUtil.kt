package com.pahadi.composenewsapp.common.util

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.pahadi.composenewsapp.base.Route

object NavigationUtil {
    fun navigateSingleTopTo(
        route: String,
        navController: NavHostController
    ){
        navController.navigate(route){// todo: what is this syntax whats usecase
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }
    }

    fun navigateToCountryScreen(
        countryId: String,
        navController: NavHostController
    ) {
        navController.navigate(Route.TopNews.routeWithoutArg + "/${countryId}/{language}/{source}"){
            launchSingleTop = true
        }
    }

    fun navigateToLanguageScreen(
        languageId: String,
        navController: NavHostController
    ) {
        navController.navigate(Route.TopNews.routeWithoutArg + "/{country}/${languageId}/{source}") {
            launchSingleTop = true
        }
    }

    fun navigateToSourceScreen(
        sourceId: String,
        navController: NavHostController
    ) {
        navController.navigate(Route.TopNews.routeWithoutArg + "/{country}/{language}/${sourceId}") {
            launchSingleTop = true
        }
    }
}