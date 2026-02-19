package com.example.ecommerceapp.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "ecommerce_database"
            )
                .fallbackToDestructiveMigration() // ✅ Recria o banco ao mudar versão
                .build()
            INSTANCE = instance
            instance
        }
    }
}