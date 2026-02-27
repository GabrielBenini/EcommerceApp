package com.example.ecommerceapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _uiState = MutableStateFlow(LoginContract.State())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<LoginContract.Effect>()
    val uiEffect = _uiEffect.asSharedFlow()

    fun handleEvent(event: LoginContract.Event) {

        when (event) {
            is LoginContract.Event.OnEmailChange -> {
                _uiState.value = _uiState.value.copy(email = event.email)
            }

            is LoginContract.Event.OnPasswordChange -> {
                _uiState.value = _uiState.value.copy(password = event.password)
            }

        }
    }

    fun login() {

        val email = uiState.value.email.trim()
        val password = uiState.value.password.trim()

        if (email.isEmpty() || password.isEmpty()) {
            viewModelScope.launch {
                _uiEffect.emit(LoginContract.Effect.ShowLoginError("Email e senha são obrigatórios"))
            }
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { login ->

                if (login.isSuccessful) {
                    viewModelScope.launch {
                        _uiEffect.emit(LoginContract.Effect.ShowLoginSuccess)
                    }
                } else {
                    val message = when (val exception = login.exception) {
                        is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException ->
                            "Email ou senha incorretos."

                        is com.google.firebase.auth.FirebaseAuthInvalidUserException ->
                            "Usuário não encontrado. Verifique o email informado."

                        is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException ->
                            "As credenciais estão incorretas."

                        is com.google.firebase.auth.FirebaseAuthUserCollisionException ->
                            "Já existe uma conta com este email."

                        is com.google.firebase.auth.FirebaseAuthWeakPasswordException ->
                            "Senha muito fraca. Escolha uma senha mais segura."

                        else ->
                            "Erro ao realizar login. Tente novamente."
                    }

                    viewModelScope.launch {
                        _uiEffect.emit(LoginContract.Effect.ShowLoginError(message))
                    }
                }
            }
    }
}