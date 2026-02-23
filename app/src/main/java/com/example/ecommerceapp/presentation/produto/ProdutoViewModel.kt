package com.example.ecommerceapp.presentation.produto

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.data.repository.ProdutoRepository
import com.example.ecommerceapp.model.Produto

class ProdutoViewModel : ViewModel() {

    private val repository = ProdutoRepository()

    val produtoList = MutableLiveData<List<Produto>>()
    val filteredList = MutableLiveData<List<Produto>>(emptyList())
    val isLoading = MutableLiveData(false)
    val searchQuery = mutableStateOf("")

    fun atualizarFiltro() {
        val query = searchQuery.value.lowercase().trim()
        val listaAtual = produtoList.value ?: emptyList()

        filteredList.value = if (query.isEmpty()) {
            listaAtual
        } else {
            listaAtual.filter { produto ->
                produto.nome.lowercase().contains(query)
            }
        }
    }

    fun carregarProdutos() {
        isLoading.value = true
        repository.buscarProdutos()
            .addOnSuccessListener { resultado ->
                val produtos = resultado.toObjects(Produto::class.java)
                produtoList.value = produtos
                filteredList.value = produtos
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
            .addOnCompleteListener {
                isLoading.value = false
            }
    }

    fun carregarProdutosPorCategoria(categoriaId: String) {
        isLoading.value = true
        repository.buscarPorCategoria(categoriaId)
            .addOnSuccessListener { resultado ->
                val produtos = resultado.toObjects(Produto::class.java)
                produtoList.value = produtos
                filteredList.value = produtos
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
            .addOnCompleteListener {
                isLoading.value = false
            }
    }
}