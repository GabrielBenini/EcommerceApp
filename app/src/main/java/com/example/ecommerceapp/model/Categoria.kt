package com.example.ecommerceapp.model

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Categoria(
    val id: String = "",
    val nome: String = "",
    val descricao: String = "",
    val imagemUrl: String = ""
)