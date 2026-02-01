package com.example.ecommerceapp.presentation.components

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.Categoria
import com.example.ecommerceapp.model.Produto

//@Preview
@Composable
fun ProdutosCard(
    produto: Produto,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .height(200.dp)


    ) {

        Column() {

            Image(
                painterResource(produto.imagem),
                contentDescription = "produtos",
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = produto.nome,
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 10.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    text = produto.preco,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )


            }


        }

    }

}


@Preview
@Composable
fun preview() {
    ProdutosCard(produto = Produto("Camisa agi", R.drawable.ic_launcher_foreground, "1390"))
}

