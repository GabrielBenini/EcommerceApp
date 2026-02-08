package com.example.ecommerceapp.presentation.detalhes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProdutoDetalhesViewModel : ViewModel() {

    private val _uiEffect = MutableSharedFlow<ProdutoDetalhesContract.Effect>()
    val uiEffect = _uiEffect.asSharedFlow()

    private val _uiState = MutableStateFlow(ProdutoDetalhesContract.State())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: ProdutoDetalhesContract.Event) {
        when (event) {
            is ProdutoDetalhesContract.Event.OnQtdProdutoChange -> {
                if (event.novaQtd < 1) {
                    viewModelScope.launch {
                        _uiEffect.emit(
                            ProdutoDetalhesContract.Effect.ShowSnackbar(
                                "A quantidade do produto nao pode ser menor que 1."
                            )
                        )
                    }
                } else {
                    _uiState.value = _uiState.value.copy(qtdProduto = event.novaQtd)
                }
            }
        }
    }

}