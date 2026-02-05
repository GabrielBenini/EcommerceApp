package com.example.ecommerceapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.presentation.Carrinho.CarrinhoScreen
import com.example.ecommerceapp.presentation.detalhes.ProdutoDetalhesScreen
import com.example.ecommerceapp.presentation.home.HomeScreen

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object Details : Destination("details")
    object Carrinho : Destination("carrinho")
    object Historico : Destination("historico")
    object Perfil : Destination("perfil")
    object Recarga : Destination("recarga")
}