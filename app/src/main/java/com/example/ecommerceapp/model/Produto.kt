package com.example.ecommerceapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Produto(
    val id: Int,
    val nome : String,
    val imagem: Int,
    val preco : String,
    val descricao: String,
)