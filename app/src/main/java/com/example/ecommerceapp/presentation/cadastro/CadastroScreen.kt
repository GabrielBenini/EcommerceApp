package com.example.ecommerceapp.presentation.cadastro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecommerceapp.navigation.Destination
import com.example.ecommerceapp.presentation.components.CadastroTextField
import com.example.ecommerceapp.ui.theme.BlueAgi

//@Preview(showBackground = true)
@Composable
fun CadastroScreen(
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
                text = "Criar Conta",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 24.dp)
            )
        }

        Text(
            modifier = Modifier
                .padding(bottom = 24.dp),
            text = "Junte se à AgiStore em segundos"
        )

        CadastroTextField(
            value = "",
            onValueChange = {},
            label = "Nome Completo"
        )

        CadastroTextField(
            value = "",
            onValueChange = {},
            label = "Email"
        )

        CadastroTextField(
            value = "",
            onValueChange = {},
            label = "Telefone"
        )

        CadastroTextField(
            value = "",
            onValueChange = {},
            label = "Data de Nascimento"
        )

        CadastroTextField(
            value = "",
            onValueChange = {},
            label = "Senha"
        )

        CadastroTextField(
            value = "",
            onValueChange = {},
            label = "Confirmar Senha"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = false,
                onCheckedChange = {}
            )

            Text(
                text = "Concordo com os Termos de Serviço e Política de Privacidade"
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = false,
                onCheckedChange = {}
            )

            Text(
                text = "Quero receber ofertas e novidades da AgiStore"
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(BlueAgi.value),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                onClick = {}
            ) {

                Text(
                    modifier = Modifier
                        .clickable(
                            onClick = { navController.navigate(Destination.Login.route) }
                        )
                        .padding(vertical = 8.dp),
                    text = "Criar Minha Conta"
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
        ) {

            Text(
                text = "Ja tem uma conta?",
            )

            Text(
                text = " Faça Login",
                fontWeight = FontWeight.SemiBold,
                color = Color(BlueAgi.value),
                modifier = Modifier
                    .clickable(
                        onClick = { navController.navigate(Destination.Login.route) }
                    )
            )

        }

    }
}
