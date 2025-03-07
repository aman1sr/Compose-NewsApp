package com.pahadi.composenewsapp.base

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.pahadi.composenewsapp.R
import com.pahadi.composenewsapp.common.util.NavigationUtil.navigateSingleTopTo
import com.pahadi.composenewsapp.common.util.ValidationUtil.checkIfValidArgNews
import com.pahadi.composenewsapp.database.entity.Article
import com.pahadi.composenewsapp.screens.NewsScreen
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@Composable
fun NewsNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination  = navBackStackEntry?.destination
    val currentScreen = bottomBarScreens.find { it.route == currentDestination?.route } ?: Route.TopNews

    Scaffold(
        topBar = {
            NewsTopBar {    // todo: didn't get the working?
                if(navController.currentBackStackEntry?.destination?.route == Route.NewsArticle.route
                    ||navController.currentBackStackEntry?.destination?.route == Route.FilterNews.route
                    || navController.currentBackStackEntry?.destination?.route == Route.TopNews.route
                    ){
                    navController.popBackStack()
                }else{
                    navigateSingleTopTo(Route.TopNews.route,navController)
                }
            }
        },
        bottomBar = {
            NewsBottomNavigation(currentScreen = currentScreen) {
                navigateSingleTopTo(it.route,navController)
            }
        }
    ) {
        NewsNavHost(
            modifier = Modifier.padding(it),
            navController = navController
        )
    }

}

@Composable
private fun NewsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController        // todo: diff bw navController & NavHostController
) {
    NavHost(navController = navController,
        startDestination = Route.TopNews.route,
        modifier = modifier
    ) {
        composable(
            route = Route.TopNews.route
        ) {
            NewsScreen { article ->
                navigatetoArticleScreen(article, navController)
            }
        }
    }
}

@Composable
private fun navigatetoArticleScreen(article: Article, navController: NavHostController) {
    val articleJsonString = Gson().toJson(article)
    val encodedArticle = URLEncoder.encode(articleJsonString, UTF_8.name())
    navController.navigate(Route.NewsArticle.routeWithoutArg + "/${encodedArticle}") {
        launchSingleTop = true
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopBar(onBackClicked: () -> Unit){
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name))},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
               Icon(
                   imageVector = Icons.Filled.ArrowBack,
                   contentDescription = null
               )
            }
        }
    )
}

@Composable
fun NewsBottomNavigation(
    currentScreen: Route,
    onIconSelected: (Route) -> Unit
){
    NavigationBar {
        bottomBarScreens.forEach { screen -> 
            NavigationBarItem(
                selected = (screen==currentScreen), 
                onClick = { onIconSelected.invoke(screen) }, 
                icon = { Icon( painter = painterResource(id = screen.icon),null) },
                label = {
                    Text(text = stringResource(id = screen.resourceId))
                }
            )
        }
    }
}