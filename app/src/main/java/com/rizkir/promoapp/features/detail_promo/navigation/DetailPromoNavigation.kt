package com.rizkir.promoapp.features.detail_promo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rizkir.promoapp.common.NavRoute
import com.rizkir.promoapp.features.detail_promo.DetailPromoRoute

/**
 * created by RIZKI RACHMANUDIN on 14/08/2023
 */
fun NavGraphBuilder.detailPromoNavigation(onNaviGateBack: () -> Unit) {
    composable(route = "${NavRoute.DetailScreen.route}/{promoId}", arguments = listOf(
        navArgument("promoId") {
            type = NavType.IntType
        },

    )) {
        DetailPromoRoute(
            onNavigateBack = onNaviGateBack
        )
    }
}