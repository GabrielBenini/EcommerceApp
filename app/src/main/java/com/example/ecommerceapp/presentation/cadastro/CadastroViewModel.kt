package com.example.ecommerceapp.presentation.cadastro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.repository.UsuarioRepository
import com.example.ecommerceapp.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CadastroViewModel : ViewModel() {

    private val repository = UsuarioRepository()
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
        val state = uiState.value

        val nomeCompleto = state.nomeCompleto.trim()
        val email = state.email.trim()
        val telefone = state.telefone.trim()
        val dataNascimento = state.dataNascimento.trim()
        val senha = state.senha.trim()
        val confirmacaoSenha = state.confirmacaoSenha.trim()

        // 🔍 Validações modernas de cadastro
        val erro: String? = when {
            nomeCompleto.isEmpty() || email.isEmpty() ||
                    telefone.isEmpty() || dataNascimento.isEmpty() ||
                    senha.isEmpty() || confirmacaoSenha.isEmpty() -> {
                "Todos os campos são obrigatórios"
            }

            nomeCompleto.split(" ").size < 2 -> {
                "Por favor, informe seu nome completo"
            }

            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                "E-mail inválido"
            }

            telefone.length < 10 -> {
                "Telefone inválido"
            }

            !Regex("""\d{2}/\d{2}/\d{4}""").matches(dataNascimento) -> {
                "Data de nascimento inválida (formato esperado: dd/MM/yyyy)"
            }

            senha.length < 8 -> {
                "A senha deve ter pelo menos 8 caracteres"
            }

            !Regex(".*[A-Z].*").containsMatchIn(senha) -> {
                "A senha deve conter pelo menos uma letra maiúscula"
            }

            !Regex(".*[0-9].*").containsMatchIn(senha) -> {
                "A senha deve conter pelo menos um número"
            }

            senha != confirmacaoSenha -> {
                "As senhas não conferem"
            }

            else -> null
        }

        if (erro != null) {
            viewModelScope.launch {
                _uiEffect.emit(CadastroContract.Effect.ShowCadastroError(erro))
            }
            return
        }

        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { cadastro ->
                if (cadastro.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: ""

                    val usuario = Usuario(
                        id = userId,
                        nomeCompleto = nomeCompleto,
                        email = email,
                        telefone = telefone,
                        dataNascimento = dataNascimento
                    )

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
                                        e.message ?: "Erro ao salvar dados do usuário"
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