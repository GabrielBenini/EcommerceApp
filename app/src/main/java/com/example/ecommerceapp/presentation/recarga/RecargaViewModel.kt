package com.example.ecommerceapp.presentation.recarga

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class RecargaViewModel : ViewModel(){

    private val _uiEffect = MutableSharedFlow<RecargaContract.Effect>()
    val uiEffect = _uiEffect.asSharedFlow()

    private val _uiState = MutableStateFlow(RecargaContract.State())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: RecargaContract.Event){
        when(event) {

            is RecargaContract.Event.OnValorRecargaChange -> {
                _uiState.value = _uiState.value.copy(valorPersonalizado = event.valor)
            }

            is RecargaContract.Event.OnRealizarRecargaClick -> {


            }

            else -> {

            }
        }
    }

}