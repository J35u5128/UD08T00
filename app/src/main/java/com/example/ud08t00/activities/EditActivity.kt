package com.example.ud08t00.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ud08t00.databinding.ActivityEditBinding
import com.squareup.picasso.Picasso

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tituloActual = intent.getStringExtra("EXTRA_TITULO")
        val imagenResId = intent.getIntExtra("EXTRA_IMAGEN_ID", 0)

        binding.etEditTitulo.setText(tituloActual)
        if (imagenResId != 0) {
            Picasso.get().load(imagenResId).into(binding.ivEditPortada)
        }
        supportActionBar?.title = "Editando: $tituloActual"

        binding.btnEditCancelar.setOnClickListener {
            setResult(RESULT_CANCELED) // Indica que no se hizo ningún cambio
            finish() // Cierra la actividad
        }

        binding.btnEditCambiar.setOnClickListener {
            val nuevoTitulo = binding.etEditTitulo.text.toString()
            if (nuevoTitulo.isNotBlank()) {
                val resultIntent = Intent().apply {
                    putExtra("EXTRA_NUEVO_TITULO", nuevoTitulo)
                }
                setResult(RESULT_OK, resultIntent) // Devuelve el nuevo título
                finish() // Cierra la actividad
            }
        }
    }
}
