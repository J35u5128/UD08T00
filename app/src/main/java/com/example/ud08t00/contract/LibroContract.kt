package com.example.ud08t00.contract

import android.provider.BaseColumns

class LibroContract {
    companion object {
        const val NOMBRE_BD = "libros.db"
        const val VERSION = 1 // Si cambias la estructura, incrementa la versión

        class Entrada : BaseColumns {
            companion object {
                const val TABLA = "libros"
                const val IDCOL = "id"
                const val IMAGENCOL = "imagen_res_id"
                const val TITULOCOL = "titulo"
                const val AUTORCOL = "autor"
                const val ANHOCOL = "anho" // Nuevo
                const val GENEROCOL = "genero" // Nuevo
                const val ISBNCOL = "isbn" // Nuevo
                const val SINOPSISCOL = "sinopsis" // Nuevo
            }
        }
    }
}
