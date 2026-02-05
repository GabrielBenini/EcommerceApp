package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdminHeader(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = { },
) {

    Column(
        modifier = Modifier
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
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Voltar",
                tint = Color.White,
                modifier = Modifier
                    .clickable(
                        onClick = navigateBack
                    )
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray.copy(alpha = 0.2f),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Admin",
                    color = Color.White,
                    modifier = Modifier
                        .padding(6.dp)
                )
            }

        }

        Column(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = "Painel Administrativo",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = "Gerencie sua loja",
                color = Color.White
            )

        }

    }

}