package com.example.ecommerceapp.presentation.perfil

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecommerceapp.navigation.Destination
import com.example.ecommerceapp.presentation.components.PerfilCard
import com.example.ecommerceapp.presentation.usuario.UsuarioViewModel
import com.example.ecommerceapp.ui.theme.BlueAgi

@Composable
fun PerfilScreen(
    navController: NavHostController,
    usuarioViewModel: UsuarioViewModel,
    modifier: Modifier = Modifier
) {

    val nomeUsuario by usuarioViewModel.nomeUsuario.observeAsState("Usuário")
    val emailUsuario by usuarioViewModel.emailUsuario.observeAsState("")


    fun obterIniciais(nome: String): String {
        val palavras = nome.trim().split(" ").filter { it.isNotEmpty() }
        return when {
            palavras.isEmpty() -> "?"
            palavras.size == 1 -> palavras[0].take(2).uppercase()
            else -> "${palavras.first().first()}${palavras.last().first()}".uppercase()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f),
            contentPadding = PaddingValues(top = 0.dp)
        ) {

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Surface(
                        modifier = Modifier
                            .size(120.dp)
                            .padding(bottom = 8.dp)
                            .clip(CircleShape),
                        color = Color(BlueAgi.value)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = obterIniciais(nomeUsuario),
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    Text(
                        text = nomeUsuario,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = emailUsuario,
                    )

                    Card(
                        modifier = Modifier.padding(top = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(BlueAgi.value).copy(alpha = 0.2f)
                        )
                    ) {
                        Text(
                            modifier = Modifier.padding(6.dp),
                            text = "Colaborador",
                            fontWeight = FontWeight.SemiBold,
                            color = Color(BlueAgi.value)
                        )
                    }
                }
            }

            item {
                PerfilCard(
                    icon = Icons.Default.Person,
                    iconColor = Color(BlueAgi.value),
                    iconBackgroundColor = Color(BlueAgi.value).copy(alpha = 0.2f),
                    text = "Meus Dados"
                )
            }

            item {
                PerfilCard(
                    icon = Icons.Default.AttachMoney,
                    iconColor = Color(0xFF33D02F),
                    iconBackgroundColor = Color(0xFF33D02F).copy(alpha = 0.2f),
                    text = "Extrato de Saldo"
                )
            }

            item {
                PerfilCard(
                    icon = Icons.Default.FavoriteBorder,
                    iconColor = Color(0xFFA530D2),
                    iconBackgroundColor = Color(0xFFA530D2).copy(alpha = 0.2f),
                    text = "Favoritos"
                )
            }

            item {
                PerfilCard(
                    icon = Icons.Default.Notifications,
                    iconColor = Color(0xFFF37925),
                    iconBackgroundColor = Color(0xFFF37925).copy(alpha = 0.2f),
                    text = "Notificações"
                )
            }

            item {
                PerfilCard(
                    icon = Icons.Default.Help,
                    iconColor = Color.Black,
                    iconBackgroundColor = Color.LightGray.copy(alpha = 0.6f),
                    text = "Ajuda"
                )
            }

            item {
                PerfilCard(
                    icon = Icons.Default.ExitToApp,
                    iconColor = Color(0xFFF52222),
                    iconBackgroundColor = Color(0xFFF52222).copy(alpha = 0.2f),
                    text = "Sair da Conta",
                    onClick = {
                        usuarioViewModel.fazerLogout()
                        navController.navigate(Destination.Login){
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}