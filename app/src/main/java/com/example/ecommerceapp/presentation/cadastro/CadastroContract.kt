package com.example.ecommerceapp.presentation.cadastro

object CadastroContract {

    data class State(
        val nomeCompleto: String = "",
        val email: String = "",
        val telefone: String = "",
        val dataNascimento: String = "",
        val senha: String = "",
        val confirmacaoSenha: String = ""
    )

    sealed interface Event {
        data class OnNomeCompletoChange(val nomeCompleto: String) : Event
        data class OnEmailChange(val email: String) : Event
        data class OnTelefoneChange(val telefone: String) : Event
        data class OnDataNascimentoChange(val dataNascimento: String) : Event
        data class OnSenhaChange(val senha: String) : Event
        data class OnConfirmacaoSenhaChange(val confirmacaoSenha: String) : Event
    }

    sealed interface Effect {
        object ShowCadastroSuccess : Effect
        data class ShowCadastroError(val message: String) : Effect
    }

}