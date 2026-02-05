package com.example.ecommerceapp.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.presentation.Carrinho.CarrinhoScreen
import com.example.ecommerceapp.presentation.components.AgiStoreHeader
import com.example.ecommerceapp.presentation.components.BottomBar
import com.example.ecommerceapp.presentation.detalhes.ProdutoDetalhesScreen
import com.example.ecommerceapp.presentation.historico.HistoricoScreen
import com.example.ecommerceapp.presentation.home.HomeScreen
import com.example.ecommerceapp.presentation.perfil.PerfilScreen
import com.example.ecommerceapp.presentation.recarga.RecargaScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppMainRoute() {
    // Main routing logic will be implemented here

    val navController = rememberNavController()

    val currentRoute = navController
        .currentBackStackEntryAsState().value
        ?.destination
        ?.route

    val hideBottomBarRoutes = listOf(
        Destination.Details.route
    )



    Scaffold(
        topBar = {
            AgiStoreHeader(navController = navController)
        },
        bottomBar = {
            if (currentRoute !in hideBottomBarRoutes) {
                BottomBar(navController = navController)
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Destination.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = Destination.Home.route) {
                HomeScreen(navController = navController)
            }

            composable(route = Destination.Details.route){
                ProdutoDetalhesScreen(navController = navController)
            }

            composable(route = Destination.Carrinho.route){
                CarrinhoScreen(navController = navController)
            }

            composable(route = Destination.Historico.route){
                HistoricoScreen(navController = navController)
            }

            composable(route = Destination.Perfil.route){
                PerfilScreen(navController = navController)
            }

            composable(route = Destination.Recarga.route){
                RecargaScreen(navController = navController)
            }



        }

    }

}