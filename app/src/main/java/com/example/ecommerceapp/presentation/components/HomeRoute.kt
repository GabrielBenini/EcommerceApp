package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ecommerceapp.presentation.home.HomeScreen

@Composable
fun HomeRoute(
    showBottomBar: Boolean
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomBar()
            }
        }
    ) { paddingValues ->

        HomeScreen(
            modifier = Modifier.padding(paddingValues)
        )
    }
}
