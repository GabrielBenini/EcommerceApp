package com.example.ecommerceapp.presentation.detalhes

object ProdutoDetalhesContract {


    data class State(

        val qtdProduto: Int = 1

    )

    sealed class Event {

        data class OnQtdProdutoChange(val novaQtd: Int) : Event()

    }

    sealed class Effect {
        // Efeitos sao acoes em resposta ao evento de um usuario, como navegar para outra tela, mostrar um toast, etc.

        data class ShowSnackbar(val message: String) : Effect()



    }

}