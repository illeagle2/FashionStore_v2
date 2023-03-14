package com.cyberwalker.fashionstore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cyberwalker.fashionstore.home.DrawerScreens
import com.cyberwalker.fashionstore.home.Home
import com.cyberwalker.fashionstore.home.HomeScreen
import com.cyberwalker.fashionstore.profile.ProfileScreenContent


@Composable
fun DrawerNavigation(navController: NavHostController){
    NavHost( navController, startDestination =  DrawerScreens.Home.route ){
        composable(DrawerScreens.Home.route){
            Home()
        }

        composable(DrawerScreens.Profile.route){
            ProfileScreenContent(modifier = Modifier){}
        }
    }
}