package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {

    BottomAppBar(
        modifier = Modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                IconButton(
                    onClick = { TODO() },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.Gray
                    )
                ) {


                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home"
                    )
                }

                Text(
                    text = "Inicio",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                IconButton(
                    onClick = { TODO() },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.Gray
                    )
                ) {


                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Carrinho"
                    )
                }

                Text(
                    text = "Carrinho",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                IconButton(
                    onClick = { TODO() },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.Gray
                    )
                ) {


                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = "Pedidos"
                    )
                }

                Text(
                    text = "Pedidos",
                    fontSize = 12.sp,
                    color = Color.Gray

                )

            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                IconButton(
                    onClick = { TODO() },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.Gray
                    )
                ) {


                    Icon(
                        imageVector = Icons.Default.MonetizationOn,
                        contentDescription = "Recarga"
                    )
                }

                Text(
                    text = "Recarga",
                    fontSize = 12.sp,
                    color = Color.Gray

                )

            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                IconButton(
                    onClick = { TODO() },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.Gray
                    )
                ) {


                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Perfil"
                    )
                }

                Text(
                    text = "Perfil",
                    fontSize = 12.sp,
                    color = Color.Gray

                )

            }


        }
    }

}