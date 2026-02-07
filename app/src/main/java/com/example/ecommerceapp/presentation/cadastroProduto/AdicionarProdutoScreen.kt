package com.example.ecommerceapp.presentation.cadastroProduto

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.presentation.components.AdicionarProdutoCard

@Preview(showBackground = true)
@Composable
fun AdicionarProdutoScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {

        Text(
            "Adicionar Produto",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Column(
            modifier = Modifier
                .padding(top = 34.dp)
                .fillMaxSize(),
        ) {

            AdicionarProdutoCard()

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) { 
                
                FloatingActionButton(
                    onClick = {

                    }
                ) {
                    Text(
                        "+",
                        fontSize = 16.sp
                    )
                }
                
            }

        }



    }
}