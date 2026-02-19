package com.example.ecommerceapp.data.local

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    // ✅ Salvar usuário logado atual
    fun salvarUsuarioLogado(userId: String) {
        prefs.edit().putString("current_user_id", userId).apply()
    }

    // ✅ Obter ID do usuário logado
    fun getUsuarioLogadoId(): String? {
        return prefs.getString("current_user_id", null)
    }

    // ✅ Salvar saldo do usuário específico
    fun salvarSaldo(userId: String, saldo: Double) {
        prefs.edit().putFloat("saldo_$userId", saldo.toFloat()).apply()
    }

    // ✅ Obter saldo do usuário específico
    fun getSaldo(userId: String): Double {
        return prefs.getFloat("saldo_$userId", 0.0f).toDouble()
    }

    // ✅ Limpar dados ao fazer logout
    fun limparDadosUsuario() {
        prefs.edit().remove("current_user_id").apply()
    }

    // ✅ Salvar dados do usuário (nome, email, etc)
    fun salvarDadosUsuario(userId: String, nome: String, email: String) {
        prefs.edit()
            .putString("nome_$userId", nome)
            .putString("email_$userId", email)
            .apply()
    }

    fun getNomeUsuario(userId: String): String {
        return prefs.getString("nome_$userId", "Usuário") ?: "Usuário"
    }

    fun getEmailUsuario(userId: String): String {
        return prefs.getString("email_$userId", "") ?: ""
    }
}