package com.example.ud08t00.contract

import android.provider.BaseColumns

class LibroContract {
    companion object {
        const val NOMBRE_BD = "libros.db"
        const val VERSION = 1

        class Entrada : BaseColumns {
            companion object {
                const val TABLA = "libros"
                const val IDCOL = "id"
                const val IMAGENCOL = "imagen_res_id"
                const val TITULOCOL = "titulo"
                const val AUTORCOL = "autor"
                const val ANHOCOL = "anho"
                const val GENEROCOL = "genero"
                const val ISBNCOL = "isbn"
                const val SINOPSISCOL = "sinopsis"
            }
        }
    }
}
