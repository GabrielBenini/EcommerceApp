package com.example.ecommerceapp.model


data class ExtratoMovimento(
    val id: String = "",
    val tipo: String = "",
    val valor: Double = 0.0,
    val metodo: String = "",
    val data: Long = System.currentTimeMillis()
)