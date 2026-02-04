package com.example.ecommerceapp.presentation.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun MetodoPagamentoCard(
    modifier: Modifier = Modifier,
    tipoPagamento: String = "",
    descricaoPagamento: String = "",
    icone: ImageVector? = null
){

    Card(
        border = BorderStroke(2.dp, color = Color.LightGray.copy(alpha = 0.3f)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .size(50.dp),
                color = Color.LightGray.copy(alpha = 0.3f)

            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (icone != null) {
                        Icon(
                            imageVector = icone,
                            contentDescription = "Icone Forma Pagamento",
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    text = tipoPagamento,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 3.dp)
                )

                Text(
                    text = descricaoPagamento
                )

            }

        }

    }

}