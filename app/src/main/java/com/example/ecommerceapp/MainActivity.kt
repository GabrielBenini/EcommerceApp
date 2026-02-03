package com.example.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ecommerceapp.navigation.AppNavigation
import com.example.ecommerceapp.presentation.Carrinho.CarrinhoScreen

import com.example.ecommerceapp.ui.theme.EcommerceAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceAppTheme {

                AppNavigation()

            }

        }
    }
}
