package com.example.ecommerceapp.data.repository

import com.example.ecommerceapp.model.Usuario
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore

class UsuarioRepository {

    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("usuarios")

    fun adicionarUsuario(usuario: Usuario): Task<Void> {
        val docRef = usersCollection.document(usuario.id.ifEmpty { usersCollection.document().id })
        return docRef.set(usuario)
    }

}