package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.R

import com.example.ecommerceapp.model.Produto

@Preview
@Composable
fun ProdutoDetalhesTop(
    modifier: Modifier = Modifier,
    produto: Produto = Produto("Produto", R.drawable.ic_launcher_foreground, "149,90", "Descricao")
    ) {


    Surface(
        modifier = modifier
            .height(250.dp),
        color = Color(0xFFE2EFFF)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .statusBarsPadding()
        ) {

            FilledIconButton(
                onClick = { TODO() },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Voltar"
                )

            }

            Spacer(Modifier.weight(1f))
            FilledIconButton(
                onClick = { TODO() },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {

                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Voltar"
                )

            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Image(
                painterResource(produto.imagem),
                contentDescription = "Imagem Produto"
            )




        }
    }

}