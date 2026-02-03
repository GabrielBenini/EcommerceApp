package com.example.ecommerceapp.presentation.detalhes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.Produto
import com.example.ecommerceapp.presentation.components.ProdutoDetalhesTop
import com.example.ecommerceapp.ui.theme.BlueAgi
import com.example.ecommerceapp.ui.theme.Verde

@Preview(showBackground = true)
@Composable
fun ProdutoDetalhesScreen(
    modifier: Modifier = Modifier,
    produto: Produto = Produto(
        1,
        "Capinha de Celular Azul",
        R.drawable.capinhaazul,
        "59,90",
        "Capinha de silicone azul para celular, proteção e estilo para o seu dispositivo."
    ),
    onBackClick: () -> Boolean? = { null },

    ) {


    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        ProdutoDetalhesTop()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 35.dp, horizontal = 16.dp)
        ) {
            Text(
                text = produto.nome,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = produto.preco,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(BlueAgi.value)
            )
        }

        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = "• Em estoque",
            color = Color(Verde.value)
        )

        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 35.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray.copy(alpha = 0.1f)
            )
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {

                Text(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    text = "Descrição",
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = produto.descricao
                )

            }

        }

        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = "Quantidade",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = { TODO() },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Gray.copy(alpha = 0.1f)
                ),
                modifier = Modifier
                    .size(50.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Remove Button"
                )

            }


            Text(
                text = "1",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(horizontal = 22.dp)
            )

            IconButton(
                onClick = { TODO() },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Gray.copy(alpha = 0.1f)
                ),
                modifier = Modifier
                    .size(50.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Button"
                )

            }

        }

        Spacer(Modifier.height(70.dp))

        HorizontalDivider(thickness = 1.dp)

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 30.dp)
        ) {

            Text(
                text = "Total",
                fontSize = 24.sp
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = produto.preco,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

        }


        Button(
            onClick = {},
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(BlueAgi.value)
            )
        ) {
            
            Row(
                modifier = Modifier
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Add cart Button"
                )



                Text(
                    modifier = Modifier
                        .padding(start = 9.dp),
                    text = "Adicionar ao Carrinho",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )

            }


        }
    }


}