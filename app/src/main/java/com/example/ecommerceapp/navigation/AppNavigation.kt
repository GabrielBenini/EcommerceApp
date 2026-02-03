package com.example.ecommerceapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.presentation.Carrinho.CarrinhoScreen
import com.example.ecommerceapp.presentation.components.BottomBar
import com.example.ecommerceapp.presentation.detalhes.ProdutoDetalhesScreen
import com.example.ecommerceapp.presentation.home.HomeScreen

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object Details : Destination("details")
    object Carrinho : Destination("carrinho")
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
                },
                navigateToCarrinho = {
                    navController.navigate(Destination.Carrinho.route)
                },


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

        composable(route = Destination.Carrinho.route){
            // Tela do carrinho de compras
            CarrinhoScreen()
        }
    }
}