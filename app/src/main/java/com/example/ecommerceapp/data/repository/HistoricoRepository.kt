package com.example.ecommerceapp.data.repository

import android.content.Context
import androidx.room.Room
import com.example.ecommerceapp.data.local.AppDatabase
import com.example.ecommerceapp.model.HistoricoCompra
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HistoricoRepository(context: Context) {

    private val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "app_database.db"
    ).build()

    private val historicoDao = db.historicoCompraDao()

    suspend fun salvarCompra(historico: HistoricoCompra) {
        withContext(Dispatchers.IO) {
            historicoDao.inserirCompra(historico)
        }
    }

    suspend fun buscarCompras(): List<HistoricoCompra> {
        return withContext(Dispatchers.IO) {
            historicoDao.buscarTodasCompras()
        }
    }
}