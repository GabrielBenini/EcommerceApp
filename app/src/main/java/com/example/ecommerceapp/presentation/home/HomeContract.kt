package com.example.ecommerceapp.presentation.home

object HomeContract {

    data class State(
        val searchProduct: String = ""
    )

    sealed class Event {

        data class OnSearchProductChange(val newSearch: String) : Event()

    }

    sealed class Effect {

    }

}
