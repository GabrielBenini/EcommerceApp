package com.example.ecommerceapp.presentation.recarga

object RecargaContract {

    data class State(
        val valorPersonalizado: String = "",
        val valorTotal: String = "R$ 0,00"
    )

    sealed class Event {
        data class OnValorRecargaChange(val valor: String) : Event()
        data class OnValorFixoSelecionado(val valorFixo: String) : Event()
        object OnRealizarRecargaClick : Event()
    }

    sealed class Effect {
        data class ShowToast(val message: String) : Effect()
    }



}