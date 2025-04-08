package com.musaan0129.assesment_mobpro1.navigation

sealed class Screen (val route: String) {
    data object Home: Screen("mainScreen")
}