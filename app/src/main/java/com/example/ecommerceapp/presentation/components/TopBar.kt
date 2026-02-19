package com.example.ecommerceapp.presentation.components

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import com.example.ecommerceapp.navigation.Destination
import com.example.ecommerceapp.presentation.usuario.UsuarioViewModel

//@Preview
@Composable
fun AgiStoreHeader(
    usuarioViewModel: UsuarioViewModel,
    navController: NavController? = null,

) {
    val nomeUsuario by usuarioViewModel.nomeUsuario.observeAsState("Usuário")
    val saldo by usuarioViewModel.saldo.observeAsState(0.0)

    fun obterIniciais(nome: String): String {
        val palavras = nome.trim().split(" ").filter { it.isNotEmpty() }
        return when {
            palavras.isEmpty() -> "?"
            palavras.size == 1 -> palavras[0].take(2).uppercase()
            else -> "${palavras.first().first()}${palavras.last().first()}".uppercase()
        }
    }
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0066CC),
                        Color(0xFF004C99)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                )
            )
            .padding(24.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Olá, colaborador! 👋",
                    fontSize = 12.sp,
                    color = Color(0xFFB3D9FF),
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "AgiStore",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color.White,
                    modifier = Modifier
                        .size(44.dp)
                        .clickable {
                            navController?.navigate(Destination.Perfil)
                        }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = obterIniciais(nomeUsuario),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0066CC)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SaldoCard(
            navController = navController,
            saldo = "R$ ${String.format("%.2f", saldo)}"
        )
    }
}