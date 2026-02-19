package com.example.ecommerceapp.presentation.usuario

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ecommerceapp.data.local.UserPreferences

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {

    private val userPrefs = UserPreferences(application)

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

    // ✅ Carregar usuário logado
    private fun carregarUsuarioLogado() {
        val userId = userPrefs.getUsuarioLogadoId()
        if (userId != null) {
            _usuarioId.value = userId
            _nomeUsuario.value = userPrefs.getNomeUsuario(userId)
            _emailUsuario.value = userPrefs.getEmailUsuario(userId)
            _saldo.value = userPrefs.getSaldo(userId)
        }
    }

    // ✅ Fazer login (chamar após autenticação bem-sucedida)
    fun fazerLogin(userId: String, nome: String, email: String, saldoInicial: Double = 0.0) {
        userPrefs.salvarUsuarioLogado(userId)
        userPrefs.salvarDadosUsuario(userId, nome, email)

        _usuarioId.value = userId
        _nomeUsuario.value = nome
        _emailUsuario.value = email

        // Carregar saldo existente ou usar inicial
        val saldoExistente = userPrefs.getSaldo(userId)
        _saldo.value = if (saldoExistente > 0) saldoExistente else saldoInicial
    }

    // ✅ Atualizar saldo (agora salva por usuário)
    fun atualizarSaldo(novoSaldo: Double) {
        val userId = _usuarioId.value ?: return
        _saldo.value = novoSaldo
        userPrefs.salvarSaldo(userId, novoSaldo)
    }

    // ✅ Descontar saldo
    fun descontarSaldo(valor: Double) {
        val userId = _usuarioId.value ?: return
        val atual = _saldo.value ?: 0.0
        val novo = (atual - valor).coerceAtLeast(0.0)
        _saldo.value = novo
        userPrefs.salvarSaldo(userId, novo)
    }

    // ✅ Adicionar saldo
    fun adicionarSaldo(valor: Double) {
        val userId = _usuarioId.value ?: return
        val atual = _saldo.value ?: 0.0
        val novo = atual + valor
        _saldo.value = novo
        userPrefs.salvarSaldo(userId, novo)
    }

    // ✅ Fazer logout
    fun fazerLogout() {
        userPrefs.limparDadosUsuario()
        _usuarioId.value = null
        _nomeUsuario.value = ""
        _emailUsuario.value = ""
        _saldo.value = 0.0
    }
}