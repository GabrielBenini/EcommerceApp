package com.example.ecommerceapp.presentation.historico

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecommerceapp.presentation.components.AgiStoreHeader
import com.example.ecommerceapp.presentation.components.BottomBar
import com.example.ecommerceapp.presentation.components.HistoricoPedidoCard

//@Preview
@Composable
fun HistoricoScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()

    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = "Histórico de Pedidos",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            HistoricoPedidoCard()


        }


    }
}
