package com.example.ecommerceapp.presentation.home

object HomeContract {

    data class State(
        val searchProduct: String = ""
    )

    sealed class Event {

        data class OnSearchProductChange(val newSearch: String) : Event()

        // object OnAddCardClicked : Event()
        // Eventos sao ações realizadas pelo usuário na tela, como clicar em um produto, adicionar ao carrinho, etc.
    }

    sealed class Effect {
        // Efeitos sao acoes em resposta ao evento de um usuario, como navegar para outra tela, mostrar um toast, etc.


    }

}

//State → representa o estado atual da tela (lista de cards, loading, etc.)
//Event → ações que o usuário faz (clicar no botão, remover card, etc.)
//Effect → ações únicas (mostrar Toast, navegar, etc.)