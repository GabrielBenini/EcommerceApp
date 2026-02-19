package com.example.ecommerceapp.model

data class CarrinhoItem(
    val produtoId: String,
    val nome: String,
    val price: Double,
    val imagem: Int,
    var quantidade: Int
)
