package com.example.ecommerceapp.utils

import androidx.room.TypeConverter
import com.example.ecommerceapp.model.CarrinhoItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromCarrinhoItemList(value: List<CarrinhoItem>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCarrinhoItemList(value: String): List<CarrinhoItem> {
        val listType = object : TypeToken<List<CarrinhoItem>>() {}.type
        return gson.fromJson(value, listType)
    }
}