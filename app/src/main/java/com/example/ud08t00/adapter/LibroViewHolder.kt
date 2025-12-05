package com.example.ud08t00.adapter

import android.view.ContextMenu
import android.view.MenuInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.ud08t00.R
import com.example.ud08t00.databinding.ItemLibroBinding
import com.example.ud08t00.model.Libro
import com.squareup.picasso.Picasso

class LibroViewHolder(private val binding: ItemLibroBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnCreateContextMenuListener {

    private lateinit var libro: Libro

    init {
        itemView.setOnCreateContextMenuListener(this)
    }

    fun render(item: Libro, onClickListener: (Libro) -> Unit) {
        this.libro = item
        binding.tvLibroTitulo.text = item.titulo
        binding.tvLibroAutor.text = item.autor
        Picasso.get().load(item.imagenResId).into(binding.ivLibroImagen)
        itemView.setOnClickListener { onClickListener(item) }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu.setHeaderTitle(libro.titulo)
        val inflater = MenuInflater(v?.context)
        inflater.inflate(R.menu.libro_context_menu, menu)
    }
}
