package com.example.ecommerceapp.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class ProdutoRepository {
    private val db = FirebaseFirestore.getInstance()
    private val produtosCollection = db.collection("produtos")

    fun buscarProdutos(): Task<QuerySnapshot> {
        return produtosCollection.get()
    }

    // Opcional: buscar produtos por categoria
    fun buscarPorCategoria(categoriaId: String): Task<QuerySnapshot> {
        return produtosCollection.whereEqualTo("categoriaId", categoriaId).get()
    }
}