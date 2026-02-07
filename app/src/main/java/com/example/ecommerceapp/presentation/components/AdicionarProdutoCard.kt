package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.Produto

@Preview
@Composable
fun AdicionarProdutoCard(
    modifier: Modifier = Modifier,
    produto: Produto = Produto(
        id = 0,
        nome = "Produto",
        imagem = R.drawable.capinhaazul,
        preco = "R$ 0,00",
        descricao = "Descrição do produto"
    )
) {

    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painterResource(produto.imagem),
                contentDescription = "Imagem do Produto",
                modifier = Modifier
                    .size(100.dp)
            )

            Column(
                modifier = Modifier
            ) {

                Text(
                    text = produto.nome,
                    modifier = Modifier
                )

                Text(
                    text = produto.descricao,
                    modifier = Modifier
                )

            }

            Spacer(Modifier.weight(1f))

            Text(
                text = produto.preco
            )


        }
    }
}