package com.example.ecommerceapp.presentation.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecommerceapp.navigation.Destination
import com.example.ecommerceapp.ui.theme.BlueAgi

//@Preview(showBackground = true)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "AgiStore",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 46.dp)
            )
        }

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Senha") },
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {

            Text(
                text = "Esqueceu a senha?",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(BlueAgi.value)
            )
        }

        Button(
            modifier = Modifier
                .padding(bottom = 44.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(BlueAgi.value),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            onClick = { navController.navigate(Destination.Home) }
        ) {

            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                text = "Entrar"
            )

        }

        HorizontalDivider(thickness = 1.dp)

        Card(
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .padding(top = 44.dp)
                .fillMaxWidth()
        ) {

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Entrar com Google"
                )

                Text(
                    text = "Continuar com Google",
                    fontWeight = FontWeight.SemiBold
                )

            }

        }

        Card(
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
        ) {

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp),
                    imageVector = Icons.Default.Login,
                    contentDescription = "Entrar com Apple"
                )

                Text(
                    text = "Continuar com Apple",
                    fontWeight = FontWeight.SemiBold
                )

            }

        }


        Row(
            modifier = Modifier
                .padding(top = 16.dp)
        ) {

            Text(
                text = "Não tem uma conta?"
            )

            Text(
                text = " Criar agora ",
                fontWeight = FontWeight.SemiBold,
                color = Color(BlueAgi.value),
                modifier = Modifier
                    .clickable(
                        onClick = { navController.navigate(Destination.Cadastro) }
                    )
            )

        }
    }
}