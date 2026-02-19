package com.example.ecommerceapp.data.local

import androidx.room.*
import com.example.ecommerceapp.model.HistoricoCompra

@Dao
interface HistoricoCompraDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirCompra(historico: HistoricoCompra)

    @Query("SELECT * FROM historico_compras WHERE usuarioId = :usuarioId ORDER BY dataCompra DESC")
    suspend fun buscarComprasPorUsuario(usuarioId: String): List<HistoricoCompra>

    @Query("SELECT * FROM historico_compras ORDER BY dataCompra DESC")
    suspend fun buscarTodasCompras(): List<HistoricoCompra>
}