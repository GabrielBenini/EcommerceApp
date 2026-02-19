package com.example.ecommerceapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Produto(
    val id: String = "",
    val nome: String = "",
    val imagemUrl: String = "",
    val preco: Double = 0.0,
    val descricao: String = "",
    val categoriaId: String = ""
)