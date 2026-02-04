package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.ui.theme.BlueAgi

@Preview
@Composable
fun ValorTotalCard(
    modifier: Modifier = Modifier,
    titulo: String = "",
    subtitulo: String= "",
    total: String= "",
    subTotalValor: String = "0,00",
    taxaServicoValor: String = "0,00",
    totalValor: String = "0,00",
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .height(130.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(titulo)
                Spacer(Modifier.weight(1f))
                Text("R$ $subTotalValor")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(subtitulo,)
                Spacer(Modifier.weight(1f))
                Text("R$ $taxaServicoValor")
            }

            HorizontalDivider(thickness = 1.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    total,
                    fontWeight = FontWeight.SemiBold
                    )
                Spacer(Modifier.weight(1f))
                Text(
                    "R$ $totalValor",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(BlueAgi.value)
                )
            }

        }


    }
}