package com.example.ecommerceapp.presentation.components

import android.R
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun DestaqueCard(
    modifier: Modifier = Modifier
) {

    val greenGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF34D399),
            Color(0xFF10B981),
            Color(0xFF059669)
        )
    )

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(greenGradient),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ){

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            
            Card(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.3f),
                    contentColor = Color.White
                )
            ) {

                Text(
                    "Ofertas da Semana",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                )
            }

            Text(
                text = "Carteira Agi Azul",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )

            Text(
                text = "Camiseta Azul com Logo Preta",
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            Text(
                text = "R$ 99,90",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )

        }

    }

}