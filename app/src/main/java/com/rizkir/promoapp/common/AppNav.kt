package com.rizkir.promoapp.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rizkir.promoapp.features.home.navigation.homeNavigation

@Composable
fun AppNav(
    navController: NavHostController,
) {

    NavHost(navController = navController, startDestination = NavRoute.HomeScreen.route) {
        homeNavigation()
    }

}