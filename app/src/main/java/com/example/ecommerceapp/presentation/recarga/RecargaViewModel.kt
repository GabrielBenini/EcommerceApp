package com.example.ecommerceapp.presentation.recarga

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class RecargaViewModel : ViewModel() {

    private val _uiEffect = MutableSharedFlow<RecargaContract.Effect>()
    val uiEffect = _uiEffect.asSharedFlow()

    private val _uiState = MutableStateFlow(RecargaContract.State())
    val uiState = _uiState.asStateFlow()

    private val _dialogMetodo = MutableStateFlow<String?>(null)
    val dialogMetodo = _dialogMetodo.asStateFlow()

    fun handleEvent(event: RecargaContract.Event) {
        when (event) {

            is RecargaContract.Event.OnValorRecargaChange -> {
                _uiState.value = _uiState.value.copy(valorPersonalizado = event.valor)
            }

            is RecargaContract.Event.OnValorFixoSelecionado -> {
                _uiState.value = _uiState.value.copy(valorPersonalizado = event.valorFixo)
            }

            is RecargaContract.Event.OnMetodoPagamentoSelecionado -> {
                _uiState.value = _uiState.value.copy(metodoSelecionado = event.metodo)
                _dialogMetodo.value = event.metodo
            }

            is RecargaContract.Event.OnRealizarRecargaClick -> {
                viewModelScope.launch {
                    _uiEffect.emit(
                        RecargaContract.Effect.ShowToast("Recarga finalizada com sucesso!")
                    )
                }
            }
        }
    }

    fun fecharDialog() {
        _dialogMetodo.value = null
    }
}