/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.cyberwalker.fashionstore.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.cyberwalker.fashionstore.data.item1
import com.cyberwalker.fashionstore.detail.DetailScreen
import com.cyberwalker.fashionstore.detail.DetailScreenActions
import com.cyberwalker.fashionstore.dump.animatedComposable
import com.cyberwalker.fashionstore.home.HomeScreen
import com.cyberwalker.fashionstore.home.HomeScreenActions
import com.cyberwalker.fashionstore.liked.LikedScreen
import com.cyberwalker.fashionstore.login.LoginScreen
import com.cyberwalker.fashionstore.profile.ProfileScreen
import com.cyberwalker.fashionstore.search.SearchScreen
import com.cyberwalker.fashionstore.splash.SplashScreen
import com.cyberwalker.fashionstore.splash.SplashScreenActions
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

sealed class Screen(val name: String, val route: String) {
    object Splash : Screen("splash", "splash")
    object Home : Screen("home", "home")
//    object Detail : Screen("detail", "detail")
    object Login: Screen("login", "login")
    object Search: Screen("search", "search")
    object Liked: Screen("liked", "liked")
    object Profile: Screen("profile", "profile")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FashionNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController(),
    ) {
    AnimatedNavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        animatedComposable(Screen.Splash.route) {
            SplashScreen(onClick = {
                navController.popBackStack()
                navController.navigate(Screen.Login.route)
            })
        }

        animatedComposable(Screen.Login.route){
            LoginScreen(onLoginClick = {
                navController.popBackStack()
                navController.navigate(Graph.HOME)
            })
        }

        animatedComposable(route = Graph.HOME){
            HomeScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"//
    const val DETAILS = "details_graph"
    const val SPLASH ="splash"
    const val LOGIN = "login"
    const val HOME = "home_graph"
//    const val AUTHENTICATION = "auth_graph"
}