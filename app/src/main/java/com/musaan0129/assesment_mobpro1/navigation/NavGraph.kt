package com.musaan0129.assesment_mobpro1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.musaan0129.assesment_mobpro1.ui.screen.AboutScreen
import com.musaan0129.assesment_mobpro1.ui.screen.MainScreen

//import com.musaan0129.mobpro2.ui.screen.AboutScreen
//import com.musaan0129.mobpro2.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route) {
            MainScreen()
        }
        composable(route = Screen.About.route) {
            AboutScreen()
        }
    }
}