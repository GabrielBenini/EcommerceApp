package com.example.ecommerceapp.presentation.carrinho

import com.example.ecommerceapp.model.CarrinhoItem

object CarrinhoContract {

    data class State(
        val itens: List<CarrinhoItem>,
        val total: Double,
        val quantidadeTotal: Int
    )

    sealed interface Event {
        object DiminuirQuantidade : Event
        object AumentarQuantidade : Event
        object ConfirmarCompra : Event
    }

    sealed interface Effect {
        data class ExibirMensagem(val mensagem: String) : Effect
        object NavegarParaPagamento : Effect
    }

}