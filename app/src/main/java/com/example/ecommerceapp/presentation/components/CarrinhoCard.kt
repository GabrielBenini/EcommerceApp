package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.Produto
import com.example.ecommerceapp.ui.theme.BlueAgi

@Preview
@Composable
fun CarrinhoCard(
    modifier: Modifier = Modifier,
    qtdeProduto: String = "1",
    produto: Produto = Produto(
        1,
        "Capinha de Celular Azul",
        R.drawable.capinhaazul,
        "59,90",
        "Capinha de silicone azul para celular, proteção e estilo para o seu dispositivo."
    )

) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .height(100.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = produto.imagem),
                contentDescription = "Imagem do produto",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )

            Column(
                modifier = Modifier
                    .width(100.dp)
                    .padding(start = 8.dp)
                    .weight(1.5f)
            ) {
                Text(
                    maxLines = 2,
                    text = produto.nome,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                )
                Text(
                    text = "R$ ${produto.preco}",
                    fontSize = 16.sp,
                    color = Color(BlueAgi.value),
                    fontWeight = FontWeight.SemiBold
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
            ) {

                IconButton(
                    onClick = { TODO() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Gray.copy(alpha = 0.1f)
                    ),
                    modifier = Modifier
                        .size(20.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Remove Button"
                    )

                }


                Text(
                    text = qtdeProduto,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(horizontal = 22.dp)
                )

                IconButton(
                    onClick = { TODO() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(BlueAgi.value),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(20.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Button"
                    )

                }

            }

        }
    }
}