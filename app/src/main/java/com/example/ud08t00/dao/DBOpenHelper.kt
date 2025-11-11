package com.example.ud08t00.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ud08t00.contract.LibroContract
import com.example.ud08t00.provider.LibroProvider

class DBOpenHelper private constructor(context: Context?) :
    SQLiteOpenHelper(context, LibroContract.NOMBRE_BD, null, LibroContract.VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // SQL para crear la tabla con todas las columnas
        val createTableSQL = """
            CREATE TABLE ${LibroContract.Companion.Entrada.TABLA} (
                ${LibroContract.Companion.Entrada.IDCOL} INTEGER PRIMARY KEY,
                ${LibroContract.Companion.Entrada.IMAGENCOL} INTEGER NOT NULL,
                ${LibroContract.Companion.Entrada.TITULOCOL} TEXT NOT NULL,
                ${LibroContract.Companion.Entrada.AUTORCOL} TEXT NOT NULL,
                ${LibroContract.Companion.Entrada.ANHOCOL} INTEGER NOT NULL,
                ${LibroContract.Companion.Entrada.GENEROCOL} TEXT NOT NULL,
                ${LibroContract.Companion.Entrada.ISBNCOL} TEXT NOT NULL,
                ${LibroContract.Companion.Entrada.SINOPSISCOL} TEXT NOT NULL
            )
        """.trimIndent()

        db.execSQL(createTableSQL)
        inicializarBBDD(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${LibroContract.Companion.Entrada.TABLA}")
        onCreate(db)
    }

    private fun inicializarBBDD(db: SQLiteDatabase) {
        val lista = LibroProvider.cargarLista()
        db.beginTransaction()
        try {
            for (libro in lista) {
                val values = ContentValues().apply {
                    put(LibroContract.Companion.Entrada.IDCOL, libro.id)
                    put(LibroContract.Companion.Entrada.IMAGENCOL, libro.imagenResId)
                    put(LibroContract.Companion.Entrada.TITULOCOL, libro.titulo)
                    put(LibroContract.Companion.Entrada.AUTORCOL, libro.autor)
                    put(LibroContract.Companion.Entrada.ANHOCOL, libro.anho)
                    put(LibroContract.Companion.Entrada.GENEROCOL, libro.genero)
                    put(LibroContract.Companion.Entrada.ISBNCOL, libro.isbn)
                    put(LibroContract.Companion.Entrada.SINOPSISCOL, libro.sinopsis)
                }
                db.insert(LibroContract.Companion.Entrada.TABLA, null, values)
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    companion object {
        private var dbOpen: DBOpenHelper? = null
        fun getInstance(context: Context): DBOpenHelper {
            if (dbOpen == null) {
                dbOpen = DBOpenHelper(context.applicationContext)
            }
            return dbOpen!!
        }
    }
}
