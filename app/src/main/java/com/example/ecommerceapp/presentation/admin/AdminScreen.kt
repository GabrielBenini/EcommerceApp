package com.example.ecommerceapp.presentation.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerceapp.presentation.components.AdminCard
import com.example.ecommerceapp.presentation.components.AdminHeader
import com.example.ecommerceapp.ui.theme.BlueAgi

@Preview(showBackground = true)
@Composable
fun AdminScreen(
    navController: NavController? = null,
    modifier: Modifier = Modifier,
    navigateBack: () -> Boolean = { false },
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        AdminHeader(
            navigateBack = {
                navigateBack()
            }
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            Text(
                text = "Ações Rápidas",
                fontWeight = FontWeight.SemiBold
            )


            AdminCard(
                icon = Icons.Default.Add,
                iconColor = Color.White,
                iconBackgroundColor = Color(BlueAgi.value),
                text = "Adicionar Produto",
                subtext = "Cadastrar novo item na loja",
                cardColor = Color(BlueAgi.value).copy(alpha = 0.2f)
            )

            AdminCard(
                icon = Icons.Default.Edit,
                iconColor = Color.White,
                iconBackgroundColor = Color(0xFFEA8A18),
                text = "Editar Produtos",
                subtext = "Modificar itens existentes",
                cardColor = Color(0xFFEA8A18).copy(alpha = 0.2f)
            )


        }
    }

}


