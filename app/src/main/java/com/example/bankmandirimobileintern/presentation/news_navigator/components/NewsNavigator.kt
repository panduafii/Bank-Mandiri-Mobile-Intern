package com.example.bankmandirimobileintern.presentation.news_navigator.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bankmandirimobileintern.R
import com.example.bankmandirimobileintern.presentation.home.HomeViewModel
import com.example.bankmandirimobileintern.presentation.navgraph.Route
import com.example.bankmandirimobileintern.presentation.search.SearchScreen
import com.example.bankmandirimobileintern.presentation.search.SearchViewModel
import com.example.bankmandirimobileintern.presentation.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember{
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when(backStackState?.destination?.route){
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NewsBottomNavigation(
            items = bottomNavigationItems,
            selectedItem = selectedItem,
            onItemClick = { index ->
                when (index) {
                    0 -> navigateToTab(
                        navController = navController,
                        route = Route.HomeScreen.route
                    )
                    1 -> navigateToTab(
                        navController = navController,
                        route = Route.SearchScreen.route
                    )

                    2 -> navigateToTab(
                        navController = navController,
                        route = Route.BookmarkScreen.route
                    )
                }
            }
        )
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles, navigate = { navigateToTab(navController = navController, route = it) })
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(state = state, event = viewModel::onEvent)
            }
            composable(route = Route.DetailsScreen.route) {

            }
            composable(route = Route.BookmarkScreen.route) {

            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}