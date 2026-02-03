package com.example.ecommerceapp.presentation.Carrinho

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.presentation.components.AgiStoreHeader
import com.example.ecommerceapp.presentation.components.BottomBar
import com.example.ecommerceapp.presentation.components.CarrinhoCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun CarrinhoScreen(
    modifier: Modifier = Modifier

) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomBar()
        }
    ) {

        Column(
            modifier = Modifier
        ) {

            AgiStoreHeader()

            Text(
                text = "Meu Carrinho",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B),
                modifier = Modifier
                    .padding(vertical = 30.dp, horizontal = 30.dp)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                CarrinhoCard()
            }

        }
    }
}
