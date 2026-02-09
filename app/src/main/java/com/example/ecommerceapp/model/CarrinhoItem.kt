package com.example.ecommerceapp.model

data class CarrinhoItem(
    val produto: Produto,
    val quantidade: Int = 1
) {
    fun calcularSubtotal(): Double {
        return (produto.preco.replace(",", ".").toDouble()) * quantidade
    }
}
