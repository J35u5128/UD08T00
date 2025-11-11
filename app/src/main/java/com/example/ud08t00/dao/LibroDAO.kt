package com.example.ud08t00.dao

import android.content.Context
import android.database.Cursor
import com.example.ud08t00.contract.LibroContract
import com.example.ud08t00.model.Libro

class LibroDAO(context: Context) {

    private val dbHelper = DBOpenHelper.getInstance(context)

    fun getAllLibros(): MutableList<Libro> {
        val listaLibros = mutableListOf<Libro>()
        val db = dbHelper.readableDatabase ?: return listaLibros

        val cursor: Cursor = db.query(
            LibroContract.Companion.Entrada.TABLA,
            null, null, null, null, null, null
        )

        cursor.use { // .use { } cierra el cursor automáticamente
            while (it.moveToNext()) {
                val libro = Libro(
                    id = it.getInt(it.getColumnIndexOrThrow(LibroContract.Companion.Entrada.IDCOL)),
                    imagenResId = it.getInt(it.getColumnIndexOrThrow(LibroContract.Companion.Entrada.IMAGENCOL)),
                    titulo = it.getString(it.getColumnIndexOrThrow(LibroContract.Companion.Entrada.TITULOCOL)),
                    autor = it.getString(it.getColumnIndexOrThrow(LibroContract.Companion.Entrada.AUTORCOL)),
                    anho = it.getInt(it.getColumnIndexOrThrow(LibroContract.Companion.Entrada.ANHOCOL)),
                    genero = it.getString(it.getColumnIndexOrThrow(LibroContract.Companion.Entrada.GENEROCOL)),
                    isbn = it.getString(it.getColumnIndexOrThrow(LibroContract.Companion.Entrada.ISBNCOL)),
                    sinopsis = it.getString(it.getColumnIndexOrThrow(LibroContract.Companion.Entrada.SINOPSISCOL))
                )
                listaLibros.add(libro)
            }
        }
        // No cierres la base de datos aquí, el sistema la gestiona.
        return listaLibros
    }
}
