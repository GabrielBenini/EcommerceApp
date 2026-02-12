package com.example.ecommerceapp.presentation.login

object LoginContract {

    data class State (
        val email: String = "",
        val password: String = ""
    )

     sealed class Event {
        data class OnEmailChange(val email: String) : Event()
        data class OnPasswordChange(val password: String) : Event()
    }

    sealed class Effect {
        object ShowLoginSuccess : Effect()
        data class ShowLoginError(val message: String) : Effect()
    }

}