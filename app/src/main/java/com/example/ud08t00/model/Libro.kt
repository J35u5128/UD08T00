package com.example.ud08t00.model

import java.io.Serializable
data class Libro(
    val id: Int,
    val imagenResId: Int,
    val titulo: String,
    val autor: String,
    val anho: Int,
    val genero: String,
    val isbn: String,
    val sinopsis: String
) : Serializable
