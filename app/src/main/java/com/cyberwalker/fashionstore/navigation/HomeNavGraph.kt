package com.cyberwalker.fashionstore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cyberwalker.fashionstore.dump.BottomNavItem
import com.cyberwalker.fashionstore.home.HomeScreenContent
import com.cyberwalker.fashionstore.liked.LikedScreenContent
import com.cyberwalker.fashionstore.profile.ProfileScreenContent
import com.cyberwalker.fashionstore.search.SearchScreenContent

@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomNavItem.Home.screen_route
    ) {
        composable(route = BottomNavItem.Home.screen_route){
            HomeScreenContent(
                modifier = Modifier,
                onAction = {
                    navController.navigate(Graph.DETAILS)
                }
            ,
            viewModel = hiltViewModel(),)
        }
        composable(route = BottomNavItem.Liked.screen_route){
            LikedScreenContent(Modifier)
        }
        composable(route = BottomNavItem.Profile.screen_route){
            ProfileScreenContent(Modifier)
        }
        composable(route = BottomNavItem.Search.screen_route){
            SearchScreenContent(Modifier)
        }
    }
}