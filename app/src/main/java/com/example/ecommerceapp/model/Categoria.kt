package com.example.ecommerceapp.model

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Categoria @SuppressLint("SupportAnnotationUsage") constructor(

    @DrawableRes val imagem: Int,
    @StringRes val nome: String

)