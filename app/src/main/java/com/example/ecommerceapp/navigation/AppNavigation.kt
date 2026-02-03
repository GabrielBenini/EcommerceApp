package com.example.ecommerceapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.presentation.components.BottomBar
import com.example.ecommerceapp.presentation.detalhes.ProdutoDetalhesScreen
import com.example.ecommerceapp.presentation.home.HomeScreen

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object Details : Destination("details")
    object Bottom : Destination("bottom")
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        startDestination = Destination.Home.route,
        navController = navController
    ) {
        composable(route = Destination.Home.route) {
            HomeScreen(
                navigateToDetailScreen = {
                    navController.navigate(Destination.Details.route)
                }
            )
        }

        composable(route = Destination.Details.route){

            ProdutoDetalhesScreen(
                navigateBack = {
                    navController.popBackStack()
                    true
                }

            )
        }

        composable(route = Destination.Bottom.route){

            BottomBar(
                navigateToCarrinho = {
                    navController.navigate(Destination.Details.route)
                }
            )

        }

    }

}