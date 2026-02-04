package com.example.ecommerceapp.presentation.perfil

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.presentation.components.AgiStoreHeader
import com.example.ecommerceapp.presentation.components.BottomBar
import com.example.ecommerceapp.presentation.components.PerfilCard
import com.example.ecommerceapp.ui.theme.BlueAgi

@Preview
@Composable
fun PerfilScreen(
    modifier: Modifier = Modifier
) {

    Scaffold(
        bottomBar = { BottomBar() }) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize()) {

            AgiStoreHeader()

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = innerPadding.calculateBottomPadding())
                    .weight(1f),
                contentPadding = PaddingValues(top = 0.dp)
            ) {

                item {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Surface(
                            modifier = Modifier
                                .size(120.dp)
                                .padding(bottom = 8.dp)
                                .clip(CircleShape),
                            color = Color(BlueAgi.value)
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    text = "GB",
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }

                        Text(
                            text = "Gabriel Benini",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                        )

                        Text(
                            text = "gabriel.benini@agibank.com",
                        )

                        Card(
                            modifier = Modifier
                                .padding(top = 8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(BlueAgi.value).copy(alpha = 0.2f)
                            )
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(6.dp),
                                text = "Colaborador",
                                fontWeight = FontWeight.SemiBold,
                                color = Color(BlueAgi.value)
                            )
                        }

                    }
                }

                item {

                    PerfilCard(
                        icon = Icons.Default.Person,
                        iconColor = Color(BlueAgi.value),
                        iconBackgroundColor = Color(BlueAgi.value).copy(alpha = 0.2f),
                        text = "Meus Dados"
                    )

                }

                item {

                    PerfilCard(
                        icon = Icons.Default.AttachMoney,
                        iconColor = Color(0xFF33D02F),
                        iconBackgroundColor = Color(BlueAgi.value).copy(alpha = 0.2f),
                        text = "Extrato de Saldo"
                    )

                }



            }
        }
    }
}