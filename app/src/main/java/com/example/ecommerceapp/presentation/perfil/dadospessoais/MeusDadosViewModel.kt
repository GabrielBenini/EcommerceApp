package com.example.ecommerceapp.presentation.perfil.dadospessoais

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MeusDadosState(
    val nome: String = "",
    val email: String = "",
    val telefone: String = "",
    val dataNascimento: String = "",
    val carregando: Boolean = true,
    val erro: String? = null
)

class MeusDadosViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val _uiState = MutableStateFlow(MeusDadosState())
    val uiState = _uiState.asStateFlow()

    fun carregarDadosUsuario() {
        val userId = auth.currentUser?.uid ?: return
        _uiState.value = _uiState.value.copy(carregando = true)

        viewModelScope.launch {
            firestore.collection("usuarios").document(userId)
                .get()
                .addOnSuccessListener { doc ->
                    val nome = doc.getString("nomeCompleto") ?: ""
                    val email = doc.getString("email") ?: ""
                    val telefone = doc.getString("telefone") ?: ""
                    val dataNascimento = doc.getString("dataNascimento") ?: ""

                    _uiState.value = MeusDadosState(
                        nome = nome,
                        email = email,
                        telefone = telefone,
                        dataNascimento = dataNascimento,
                        carregando = false
                    )
                }
                .addOnFailureListener { e ->
                    _uiState.value = _uiState.value.copy(
                        erro = e.message ?: "Erro ao carregar os dados",
                        carregando = false
                    )
                }
        }
    }

    fun atualizarDadosUsuario(nome: String, email: String, telefone: String) {
        val userId = auth.currentUser?.uid ?: return
        val updates = mapOf(
            "nomeCompleto" to nome,
            "email" to email,
            "telefone" to telefone
        )

        firestore.collection("usuarios").document(userId)
            .update(updates)
            .addOnSuccessListener {
                _uiState.value = _uiState.value.copy(
                    nome = nome,
                    email = email,
                    telefone = telefone
                )
            }
            .addOnFailureListener { e ->
                _uiState.value = _uiState.value.copy(erro = e.message ?: "Erro ao atualizar dados")
            }
    }
}