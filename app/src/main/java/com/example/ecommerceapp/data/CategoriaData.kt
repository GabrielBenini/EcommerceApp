package com.example.ecommerceapp.data

import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.Categoria

object CategoriaData {

    val categoriaList = listOf<Categoria>(
        Categoria(R.drawable.ic_launcher_foreground, "Todos"),
        Categoria(R.drawable.ic_launcher_foreground, "Bebidas"),
        Categoria(R.drawable.ic_launcher_foreground, "Snacks"),
        Categoria(R.drawable.ic_launcher_foreground, "Frutas"),
        Categoria(R.drawable.ic_launcher_foreground, "Higiene"),
    )

}