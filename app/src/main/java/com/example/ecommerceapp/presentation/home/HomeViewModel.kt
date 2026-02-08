package com.example.ecommerceapp.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class   HomeViewModel : ViewModel() {

    private val _uiEffect = MutableSharedFlow<HomeContract.Effect>()
    val uiEffect = _uiEffect.asSharedFlow()

    private val _uiState = MutableStateFlow(HomeContract.State())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnSearchProductChange -> {

                _uiState.update { currentState ->
                    currentState.copy(searchProduct = event.newSearch)
                }
            }
        }
    }


}