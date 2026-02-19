package com.example.ecommerceapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historico_compras")
data class HistoricoCompra(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val usuarioId: String,
    val dataCompra: Long,
    val itens: List<CarrinhoItem>,
    val total: Double
)