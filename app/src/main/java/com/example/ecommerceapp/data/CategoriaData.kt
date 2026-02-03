package com.example.ecommerceapp.data

import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.Categoria

object CategoriaData {

    val categoriaList = listOf<Categoria>(
        Categoria(R.drawable.categoria_todos, "Todos"),
        Categoria(R.drawable.categoria_camiseta, "Camisetas"),
        Categoria(R.drawable.categoria_carteiras, "Carteiras"),
        Categoria(R.drawable.categoria_mochilas, "Mochilas"),
        Categoria(R.drawable.categoria_capinhas, "Capinhas"),
        Categoria(R.drawable.categoria_xicaras, "Xícaras"),
    )

}