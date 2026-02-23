package com.example.ecommerceapp.presentation.usuario

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ecommerceapp.data.local.UserPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {

    private val userPrefs = UserPreferences(application)
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val _usuarioId = MutableLiveData<String?>()
    val usuarioId: LiveData<String?> = _usuarioId

    private val _nomeUsuario = MutableLiveData<String>()
    val nomeUsuario: LiveData<String> = _nomeUsuario

    private val _emailUsuario = MutableLiveData<String>()
    val emailUsuario: LiveData<String> = _emailUsuario

    private val _saldo = MutableLiveData<Double>()
    val saldo: LiveData<Double> get() = _saldo

    init {
        carregarUsuarioLogado()
    }

    private fun carregarUsuarioLogado() {
        val userId = userPrefs.getUsuarioLogadoId()
        if (userId != null) {
            _usuarioId.value = userId
            _nomeUsuario.value = userPrefs.getNomeUsuario(userId)
            _emailUsuario.value = userPrefs.getEmailUsuario(userId)
            _saldo.value = userPrefs.getSaldo(userId)

            carregarDadosFirestore(userId)
        } else {
            auth.currentUser?.let { user ->
                _usuarioId.value = user.uid
                _nomeUsuario.value = user.displayName ?: "Usuário"
                _emailUsuario.value = user.email ?: ""
                carregarDadosFirestore(user.uid)
            }
        }
    }

    private fun carregarDadosFirestore(userId: String) {
        firestore.collection("usuarios").document(userId)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    val nome = doc.getString("nomeCompleto") ?: _nomeUsuario.value.orEmpty()
                    val email = doc.getString("email") ?: _emailUsuario.value.orEmpty()
                    val saldo = doc.getDouble("saldo") ?: 0.0

                    _nomeUsuario.value = nome
                    _emailUsuario.value = email
                    _saldo.value = saldo

                    // Atualiza o cache local
                    userPrefs.salvarDadosUsuario(userId, nome, email)
                    userPrefs.salvarSaldo(userId, saldo)
                }
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

    fun fazerLogin(userId: String, nome: String, email: String, saldoInicial: Double = 0.0) {
        userPrefs.salvarUsuarioLogado(userId)
        userPrefs.salvarDadosUsuario(userId, nome, email)

        _usuarioId.value = userId
        _nomeUsuario.value = nome
        _emailUsuario.value = email

        val saldoExistente = userPrefs.getSaldo(userId)
        val saldoFinal = if (saldoExistente > 0) saldoExistente else saldoInicial
        _saldo.value = saldoFinal
        userPrefs.salvarSaldo(userId, saldoFinal)

        val userDoc = firestore.collection("usuarios").document(userId)
        userDoc.get()
            .addOnSuccessListener { snapshot ->
                val telefone = snapshot.getString("telefone") ?: ""
                val dataNascimento = snapshot.getString("dataNascimento") ?: ""

                val dadosUsuario = mapOf(
                    "id" to userId,
                    "nomeCompleto" to nome,
                    "email" to email,
                    "telefone" to telefone,
                    "dataNascimento" to dataNascimento,
                    "saldo" to saldoFinal
                )

                // 🔹 usa merge para atualizar só campos novos sem apagar os existentes
                userDoc.set(dadosUsuario, com.google.firebase.firestore.SetOptions.merge())
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

    fun atualizarSaldo(novoSaldo: Double) {
        val userId = _usuarioId.value ?: return
        _saldo.value = novoSaldo
        userPrefs.salvarSaldo(userId, novoSaldo)

        firestore.collection("usuarios").document(userId)
            .update("saldo", novoSaldo)
            .addOnSuccessListener {
                println("✅ Saldo atualizado com sucesso (R$ $novoSaldo)")
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

    fun descontarSaldo(valor: Double) {
        val userId = _usuarioId.value ?: return
        val atual = _saldo.value ?: 0.0
        val novo = (atual - valor).coerceAtLeast(0.0)
        atualizarSaldo(novo)
    }

    fun adicionarSaldo(valor: Double) {
        val userId = _usuarioId.value ?: return
        val atual = _saldo.value ?: 0.0
        val novo = atual + valor
        atualizarSaldo(novo)
    }

    fun escutarSaldoEmTempoReal() {
        val userId = _usuarioId.value ?: return
        firestore.collection("usuarios")
            .document(userId)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    e.printStackTrace()
                    return@addSnapshotListener
                }
                if (snapshot != null && snapshot.exists()) {
                    val saldo = snapshot.getDouble("saldo") ?: 0.0
                    _saldo.postValue(saldo)
                    userPrefs.salvarSaldo(userId, saldo)
                }
            }
    }

    fun fazerLogout() {
        auth.signOut()
        userPrefs.limparDadosUsuario()
        _usuarioId.value = null
        _nomeUsuario.value = ""
        _emailUsuario.value = ""
        _saldo.value = 0.0
    }
}