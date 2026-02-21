package com.example.ecommerceapp.presentation.produto

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.data.repository.ProdutoRepository
import com.example.ecommerceapp.model.Produto

class ProdutoViewModel(
    private val repository: ProdutoRepository
) : ViewModel() {


    val produtoList = MutableLiveData<List<Produto>>()
    val filteredList = MutableLiveData<List<Produto>>(emptyList())
    val isLoading = MutableLiveData(false)
    val searchQuery = mutableStateOf("")

    // 🔹 Atualiza o filtro de produtos conforme o texto digitado
    fun atualizarFiltro() {
        val query = searchQuery.value.lowercase().trim()
        val listaAtual = produtoList.value ?: emptyList()

        filteredList.value = if (query.isEmpty()) {
            listaAtual    // mostra tudo se a busca estiver vazia
        } else {
            listaAtual.filter { produto ->
                produto.nome.lowercase().contains(query)
            }
        }
    }

    // 🔹 Carrega todos os produtos do Firestore
    fun carregarProdutos() {
        isLoading.value = true
        repository.buscarProdutos()
            .addOnSuccessListener { resultado ->
                val produtos = resultado.toObjects(Produto::class.java)
                produtoList.value = produtos
                filteredList.value = produtos // ✅ mostra todos assim que carregar
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
            .addOnCompleteListener {
                isLoading.value = false
            }
    }

    // 🔹 Carrega produtos filtrando por categoria
    fun carregarProdutosPorCategoria(categoriaId: String) {
        isLoading.value = true
        repository.buscarPorCategoria(categoriaId)
            .addOnSuccessListener { resultado ->
                val produtos = resultado.toObjects(Produto::class.java)
                produtoList.value = produtos
                filteredList.value = produtos // ✅ mostra todos da categoria logo de início
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
            .addOnCompleteListener {
                isLoading.value = false
            }
    }
}