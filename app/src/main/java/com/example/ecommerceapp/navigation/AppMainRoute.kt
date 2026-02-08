package com.example.ecommerceapp.navigation

import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ecommerceapp.model.Produto
import com.example.ecommerceapp.presentation.carrinho.CarrinhoScreen
import com.example.ecommerceapp.presentation.cadastro.CadastroScreen
import com.example.ecommerceapp.presentation.components.AgiStoreHeader
import com.example.ecommerceapp.presentation.components.BottomBar
import com.example.ecommerceapp.presentation.detalhes.ProdutoDetalhesScreen
import com.example.ecommerceapp.presentation.historico.HistoricoScreen
import com.example.ecommerceapp.presentation.home.HomeScreen
import com.example.ecommerceapp.presentation.login.LoginScreen
import com.example.ecommerceapp.presentation.perfil.PerfilScreen
import com.example.ecommerceapp.presentation.recarga.RecargaScreen
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Composable
fun AppMainRoute() {

    val navController = rememberNavController()

    val currentBackStackEntry = navController
        .currentBackStackEntryAsState().value

    val currentRoute = currentBackStackEntry?.destination?.route

    val isDetailsScreen = currentBackStackEntry?.destination?.route?.contains("Details") == true

    val hideBottomBarRoutes = listOf(
        Destination.Login::class.qualifiedName,
        Destination.Cadastro::class.qualifiedName,
        Destination.Admin::class.qualifiedName,
        Destination.Details::class.qualifiedName
    )

    val hideTopBarRoutes = listOf(
        Destination.Login::class.qualifiedName,
        Destination.Cadastro::class.qualifiedName,
        Destination.Admin::class.qualifiedName,
        Destination.Details::class.qualifiedName
    )
    Scaffold(
        topBar = {
            if (currentRoute !in hideTopBarRoutes && !isDetailsScreen) {
                AgiStoreHeader(navController = navController)
            }
        },
        bottomBar = {
            if (currentRoute !in hideBottomBarRoutes && !isDetailsScreen) {
                BottomBar(navController = navController)
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Destination.Login,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable<Destination.Login> {
                LoginScreen(navController = navController)
            }

            composable<Destination.Cadastro> {
                CadastroScreen(navController = navController)
            }

            composable<Destination.Home> {
                HomeScreen(
                    navigateToDetailScreen = { produto ->
                        navController.navigate(Destination.Details(produto))
                    }
                )
            }

            composable<Destination.Details> (
                typeMap = mapOf(typeOf<Produto>() to ProductNavType))
            { backStackEntry ->
                val produto = backStackEntry.toRoute<Destination.Details>().produto
                ProdutoDetalhesScreen(
                    produto = produto,
                    navController = navController,
                    navigateBack = {
                        navController.popBackStack()
                        true
                    }
                )
            }

            composable<Destination.Carrinho> {
                CarrinhoScreen(navController = navController)
            }

            composable<Destination.Historico> {
                HistoricoScreen(navController = navController)
            }

            composable<Destination.Perfil> {
                PerfilScreen(navController = navController)
            }

            composable<Destination.Recarga> {
                RecargaScreen(navController = navController)
            }
        }

    }

}

val ProductNavType = object : NavType<Produto>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Produto? {
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): Produto {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: Produto): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: Produto) {
        bundle.putString(key, Json.encodeToString(value))
    }
}

sealed interface Destination {
    @Serializable
    data object Home : Destination

    @Serializable
    data class Details(val produto: Produto) : Destination

    @Serializable
    data object Carrinho : Destination

    @Serializable
    data object Historico : Destination

    @Serializable
    data object Perfil : Destination

    @Serializable
    data object Recarga : Destination

    @Serializable
    data object Login : Destination

    @Serializable
    data object Cadastro : Destination

    @Serializable
    data object Admin : Destination
}