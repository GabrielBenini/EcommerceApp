package com.example.ecommerceapp.presentation.usuario

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ecommerceapp.data.local.UserPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {

    private val userPrefs = UserPreferences(application)
    val firestore = FirebaseFirestore.getInstance()
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

                    userPrefs.salvarDadosUsuario(userId, nome, email)
                    userPrefs.salvarSaldo(userId, saldo)
                }
            }
            .addOnFailureListener { it.printStackTrace() }
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
                userDoc.set(dadosUsuario, SetOptions.merge())
            }
            .addOnFailureListener { it.printStackTrace() }
    }

    fun registrarMovimentoSaldo(tipo: String, valor: Double, metodo: String) {
        val userId = _usuarioId.value ?: return
        val movimento = mapOf(
            "tipo" to tipo,
            "valor" to valor,
            "metodo" to metodo,
            "data" to System.currentTimeMillis()
        )

        firestore.collection("usuarios")
            .document(userId)
            .collection("extrato")
            .add(movimento)
            .addOnSuccessListener {
                println("✅ Movimento de saldo registrado: $tipo - R$ $valor via $metodo")
            }
            .addOnFailureListener { it.printStackTrace() }
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
            .addOnFailureListener { it.printStackTrace() }
    }

    fun descontarSaldo(valor: Double, metodo: String = "Compra") {
        val userId = _usuarioId.value ?: return
        val atual = _saldo.value ?: 0.0
        val novo = (atual - valor).coerceAtLeast(0.0)
        atualizarSaldo(novo)
        registrarMovimentoSaldo(tipo = "compra", valor = -valor, metodo = metodo)
    }

    fun adicionarSaldo(valor: Double, metodo: String) {
        val userId = _usuarioId.value ?: return
        val atual = _saldo.value ?: 0.0
        val novo = atual + valor

        atualizarSaldo(novo)

        val metodoFormatado = if (metodo.equals("Recarga", ignoreCase = true)) "PIX" else metodo

        registrarMovimentoSaldo(
            tipo = "recarga",
            valor = valor,
            metodo = metodoFormatado
        )
    }

    fun alternarFavorito(produtoId: String, produtoData: Map<String, Any>) {
        val userId = _usuarioId.value ?: return
        val favoritoRef = firestore.collection("usuarios")
            .document(userId)
            .collection("favoritos")
            .document(produtoId)

        favoritoRef.get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    favoritoRef.delete()
                        .addOnSuccessListener { println("❌ Produto removido dos favoritos") }
                } else {
                    favoritoRef.set(produtoData)
                        .addOnSuccessListener { println("❤️ Produto adicionado aos favoritos") }
                }
            }
    }

    fun verificarFavorito(produtoId: String, onResult: (Boolean) -> Unit) {
        val userId = _usuarioId.value ?: return
        val docRef = firestore.collection("usuarios")
            .document(userId)
            .collection("favoritos")
            .document(produtoId)

        docRef.get()
            .addOnSuccessListener { onResult(it.exists()) }
            .addOnFailureListener { onResult(false) }
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