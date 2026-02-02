package com.example.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ecommerceapp.presentation.components.HomeRoute
import com.example.ecommerceapp.presentation.login.LoginScreen
import com.example.ecommerceapp.ui.theme.EcommerceAppTheme
import com.google.firebase.Firebase
import com.google.firebase.FirebaseError
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceAppTheme {
                HomeRoute(showBottomBar = true)
            }
        }
    }
}
