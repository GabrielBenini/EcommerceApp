package com.example.ecommerceapp.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.Produto
@Preview(showBackground = true)
@Composable
fun ProdutosCard(
    produto: Produto = Produto(
        id = "1",
        nome = "Produto Exemplo",
        descricao = "Descrição do produto exemplo",
        preco = 99.99,
        imagemUrl = "https://via.placeholder.com/150"
    ),
    modifier: Modifier = Modifier,
    onClick: (Produto) -> Unit = { Log.d("ProdutosCard", "Produto clicado: ${it.nome}") }
) {
    Card(
        modifier = modifier
            .clickable { onClick(produto) }
            .background(Color.Transparent)
            .size(width = 150.dp, height = 210.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(produto.imagemUrl)
                    .build(),
                contentDescription = produto.nome,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(150.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = produto.nome,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "R$ %.2f".format(produto.preco),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}