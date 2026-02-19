package com.example.ecommerceapp.presentation.cadastro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.repository.UsuarioRepository
import com.example.ecommerceapp.model.Usuario
import com.example.ecommerceapp.presentation.login.LoginContract
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CadastroViewModel : ViewModel() {

    private val repository = UsuarioRepository()
    val usuarioSalvo = MutableLiveData<Boolean>()
    private val auth = FirebaseAuth.getInstance()

    private val _uiState = MutableStateFlow(CadastroContract.State())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<CadastroContract.Effect>()
    val uiEffect = _uiEffect.asSharedFlow()

    fun handleEvent(event: CadastroContract.Event) {
        when (event) {
            is CadastroContract.Event.OnNomeCompletoChange -> {
                _uiState.value = _uiState.value.copy(nomeCompleto = event.nomeCompleto)
            }

            is CadastroContract.Event.OnEmailChange -> {
                _uiState.value = _uiState.value.copy(email = event.email)
            }

            is CadastroContract.Event.OnTelefoneChange -> {
                _uiState.value = _uiState.value.copy(telefone = event.telefone)
            }

            is CadastroContract.Event.OnDataNascimentoChange -> {
                _uiState.value = _uiState.value.copy(dataNascimento = event.dataNascimento)
            }

            is CadastroContract.Event.OnSenhaChange -> {
                _uiState.value = _uiState.value.copy(senha = event.senha)
            }

            is CadastroContract.Event.OnConfirmacaoSenhaChange -> {
                _uiState.value = _uiState.value.copy(confirmacaoSenha = event.confirmacaoSenha)
            }
        }
    }

    fun cadastro() {

        val nomeCompleto = uiState.value.nomeCompleto.trim()
        val email = uiState.value.email.trim()
        val telefone = uiState.value.telefone.trim()
        val dataNascimento = uiState.value.dataNascimento.trim()
        val senha = uiState.value.senha.trim()
        val confirmacaoSenha = uiState.value.confirmacaoSenha.trim()

        if (nomeCompleto.isEmpty() || email.isEmpty() || telefone.isEmpty() || dataNascimento.isEmpty() || senha.isEmpty() || confirmacaoSenha.isEmpty()) {
            viewModelScope.launch {
                _uiEffect.emit(CadastroContract.Effect.ShowCadastroError("Todos os campos são obrigatórios"))
            }
        }

        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { cadastro ->
                if (cadastro.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: ""

                    // Criar objeto do Usuario
                    val usuario = Usuario(
                        id = userId,
                        nomeCompleto = nomeCompleto,
                        email = email,
                        telefone = telefone,
                        dataNascimento = dataNascimento
                    )

                    // Salvar no Firestore
                    repository.adicionarUsuario(usuario)
                        .addOnSuccessListener {
                            viewModelScope.launch {
                                _uiEffect.emit(CadastroContract.Effect.ShowCadastroSuccess)
                            }
                        }
                        .addOnFailureListener { e ->
                            viewModelScope.launch {
                                _uiEffect.emit(
                                    CadastroContract.Effect.ShowCadastroError(
                                        e.message ?: "Erro ao salvar os dados do usuário"
                                    )
                                )
                            }
                        }
                } else {
                    viewModelScope.launch {
                        _uiEffect.emit(
                            CadastroContract.Effect.ShowCadastroError(
                                cadastro.exception?.message ?: "Erro ao realizar o cadastro"
                            )
                        )
                    }
                }
            }
    }
}