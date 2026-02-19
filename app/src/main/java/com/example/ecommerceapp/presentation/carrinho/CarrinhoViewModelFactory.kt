package com.example.ecommerceapp.presentation.carrinho

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceapp.presentation.usuario.UsuarioViewModel

class CarrinhoViewModelFactory(
    private val application: Application,
    private val usuarioViewModel: UsuarioViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarrinhoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CarrinhoViewModel(application, usuarioViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}