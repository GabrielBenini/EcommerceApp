package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ecommerceapp.model.Produto
import com.example.ecommerceapp.ui.theme.BlueAgi

@Composable
fun CarrinhoCard(
    produto: Produto,
    qtdeProduto: String,
    modifier: Modifier = Modifier,
    onAdd: () -> Unit = {},
    onRemove: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .height(100.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = produto.imagemUrl,
                contentDescription = produto.nome,
                modifier = Modifier
                    .size(100.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    maxLines = 2,
                    text = produto.nome,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 12.dp)
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
                modifier = Modifier.weight(1f)
            ) {
                IconButton(
                    onClick = onRemove,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Gray.copy(alpha = 0.1f)
                    ),
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(imageVector = Icons.Default.Remove, contentDescription = "Remover")
                }

                Text(
                    text = qtdeProduto,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 22.dp)
                )

                IconButton(
                    onClick = onAdd,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(BlueAgi.value),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Adicionar")
                }
            }
        }
    }
}