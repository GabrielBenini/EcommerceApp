package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.nio.file.WatchEvent

@Preview
@Composable
fun HistoricoPedidoCard(
    modifier: Modifier = Modifier,
    total: String = "R$ 0,00"
) {

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Surface(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
                    .size(50.dp),
                color = Color(0xFFA9FA9B).copy(alpha = 0.5f)

            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "Carrinho",
                        tint = Color(0xFF448D38),
                        modifier = Modifier
                    )
                }
            }

            Text(
                text = "Pedido",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {

                Text(
                    text = "1x Capinha de Celular Azul"
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            HorizontalDivider(thickness = 1.dp)

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    "Total",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                )

                Spacer(Modifier.weight(1f))

                Text(
                    text = "$total",
                )

            }
        }
    }
}