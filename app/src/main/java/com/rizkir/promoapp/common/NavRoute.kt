package com.rizkir.promoapp.common

sealed class NavRoute(var route: String) {
    object HomeScreen : NavRoute("homeScreen")
    object DetailScreen : NavRoute("detailScreen")
}
