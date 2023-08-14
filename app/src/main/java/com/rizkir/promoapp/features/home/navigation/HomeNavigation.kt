package com.rizkir.promoapp.features.home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rizkir.promoapp.common.NavRoute
import com.rizkir.promoapp.features.home.HomeRoute
import com.rizkir.promoapp.features.home.HomeViewModel

fun NavController.navigateToDetailPromo(promoId: Int) {
    this.navigate(NavRoute.DetailScreen.route + "/$promoId")
}
fun NavGraphBuilder.homeNavigation(onPromoClick: (Int) -> Unit) {
    composable(route = NavRoute.HomeScreen.route) {
        HomeRoute(onPromoClick = onPromoClick)
    }
}