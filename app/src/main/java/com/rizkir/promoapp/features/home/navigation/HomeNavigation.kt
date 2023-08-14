package com.rizkir.promoapp.features.home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rizkir.promoapp.common.NavRoute
import com.rizkir.promoapp.features.home.HomeRoute
import com.rizkir.promoapp.features.home.HomeViewModel

fun NavGraphBuilder.homeNavigation() {
    composable(route = NavRoute.HomeScreen.route, arguments = listOf(
        navArgument("movieId") {
            type = NavType.IntType
        }
    )) {
        val viewModel: HomeViewModel = hiltViewModel()
        HomeRoute(viewModel)
    }
}