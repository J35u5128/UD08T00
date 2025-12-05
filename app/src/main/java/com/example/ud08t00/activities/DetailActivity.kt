package com.example.ud08t00.activities

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ud08t00.databinding.ActivityDetailBinding // Importa la clase de binding
import com.example.ud08t00.model.Libro
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val libro = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_LIBRO", Libro::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_LIBRO") as? Libro
        }

        if (libro != null) {
            Picasso.get().load(libro.imagenResId).into(binding.ivDetailPortada)

            binding.tvDetailTitulo.text = libro.titulo
            binding.tvDetailAutor.text = libro.autor
            binding.tvDetailAnho.text = libro.anho.toString()
            binding.tvDetailGenero.text = libro.genero
            binding.tvDetailDescripcion.text = libro.sinopsis

            supportActionBar?.title = libro.titulo
        }
    }
}
