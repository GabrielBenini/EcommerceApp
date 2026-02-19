package com.example.ecommerceapp.presentation.categoria

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.data.repository.CategoriaRepository
import com.example.ecommerceapp.model.Categoria

class CategoriaViewModel : ViewModel() {

    private val repository = CategoriaRepository()

    val categoriaList = MutableLiveData<List<Categoria>>()
    val isLoading = MutableLiveData(false) // 👈 NOVO

    fun carregarCategorias() {
        isLoading.value = true // 👈 ativa o loading

        repository.buscarCategorias()
            .addOnSuccessListener { resultado ->
                val categorias = resultado.toObjects(Categoria::class.java)
                categoriaList.value = categorias
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
            .addOnCompleteListener {
                isLoading.value = false // 👈 desativa o loading
            }
    }
}