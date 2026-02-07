package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ecommerceapp.navigation.Destination
import com.example.ecommerceapp.ui.theme.BlueAgi

//@Preview
@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val currentRoute = navController
        .currentBackStackEntryAsState().value
        ?.destination?.route

    BottomAppBar(modifier = modifier) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomBarItem(
                selected = currentRoute == Destination.Home::class.qualifiedName,
                icon = Icons.Default.Home,
                label = "Início"
            ) {
                navController.navigate(Destination.Home) {
                    popUpTo(Destination.Home) { inclusive = false }
                    launchSingleTop = true
                }
            }

            BottomBarItem(
                selected = currentRoute == Destination.Carrinho::class.qualifiedName,
                icon = Icons.Default.ShoppingCart,
                label = "Carrinho"
            ) {
                navController.navigate(Destination.Carrinho) {
                    launchSingleTop = true
                }
            }

            BottomBarItem(
                selected = currentRoute == Destination.Historico::class.qualifiedName,
                icon = Icons.Default.History,
                label = "Histórico"
            ) {
                navController.navigate(Destination.Historico)
            }

            BottomBarItem(
                selected = currentRoute == Destination.Recarga::class.qualifiedName,
                icon = Icons.Default.MonetizationOn,
                label = "Recarga"
            ) {
                navController.navigate(Destination.Recarga)
            }

            BottomBarItem(
                selected = currentRoute == Destination.Perfil::class.qualifiedName,
                icon = Icons.Default.Person,
                label = "Perfil"
            ) {
                navController.navigate(Destination.Perfil)
            }
        }
    }
}


@Composable
fun BottomBarItem(
    selected: Boolean,
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
){

    val color = if (selected) Color(BlueAgi.value) else Color.Gray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(
            onClick = onClick
        ) {
            Icon(
                icon,
                contentDescription = label,
                tint = color
            )
        }

        Text(
            text = label,
            fontSize = 12.sp,
            color = color
        )

    }

}