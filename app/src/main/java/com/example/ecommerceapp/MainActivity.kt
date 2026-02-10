package com.example.ecommerceapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ecommerceapp.navigation.AppMainRoute

import com.example.ecommerceapp.ui.theme.EcommerceAppTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val auth = Firebase.auth
        Log.i(TAG, "onCreate: ${auth.currentUser?.email}")
        auth.signInWithEmailAndPassword(
            "gabriel.benini@gmail.com",
            "123456"
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.i(TAG, "onCreate: Usuário criado com sucesso")
            } else {
                Log.e(TAG, "onCreate: Falha ao criar usuário", task.exception)
            }
        }

        setContent {
            EcommerceAppTheme {

                AppMainRoute()

            }

        }
    }
}
