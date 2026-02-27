package com.example.ecommerceapp.presentation.detalhes

object ProdutoDetalhesContract {

    data class State(
        val qtdProduto: Int = 1
    )

    sealed class Event {
        data class OnQtdProdutoChange(val novaQtd: Int) : Event()
    }

    sealed class Effect {
        data class ShowSnackbar(val message: String) : Effect()
    }
}