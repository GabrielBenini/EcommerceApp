package com.example.ecommerceapp.presentation.carrinho

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecommerceapp.presentation.components.CarrinhoCard
import com.example.ecommerceapp.presentation.components.ValorTotalCard
import com.example.ecommerceapp.ui.theme.BlueAgi

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun CarrinhoScreen(
        navController: NavHostController,
    modifier: Modifier = Modifier

) {


    LazyColumn(
        modifier = Modifier
    ) {

        item {

            Text(
                text = "Meu Carrinho",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B),
                modifier = Modifier
                    .padding(vertical = 30.dp, horizontal = 30.dp)
                    .fillMaxWidth()
            )

        }

        item {

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                CarrinhoCard()
                CarrinhoCard()
            }
        }

        item {

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                ValorTotalCard(
                    titulo = "Subtotal",
                    subtitulo = "Taxa de Serviço",
                    total = "Total",
                    subTotalValor = "150,00",
                    taxaServicoValor = "10,00",
                    totalValor = "160,00"
                )
            }

        }

        item {

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = { TODO() },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(BlueAgi.value)
                )
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(4.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Ícone de cadeado",
                        tint = Color.White,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 8.dp)
                    )

                    Text(
                        text = "Confirmar Compra",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )

                }

            }

        }


    }
}

