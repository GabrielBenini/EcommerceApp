package com.example.ecommerceapp.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class CategoriaRepository {
    private val db = FirebaseFirestore.getInstance()
    private val categoriasCollection = db.collection("categorias")

    fun buscarCategorias(): Task<QuerySnapshot> {
        return categoriasCollection.get()
    }
}