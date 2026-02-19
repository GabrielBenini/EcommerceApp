package com.example.ecommerceapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ecommerceapp.model.HistoricoCompra
import com.example.ecommerceapp.utils.Converters

@Database(
    entities = [HistoricoCompra::class],
    version = 2, // ✅ Incremente a versão (era 1, agora 2)
    exportSchema = false
)
@TypeConverters(value = [Converters::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun historicoCompraDao(): HistoricoCompraDao
}