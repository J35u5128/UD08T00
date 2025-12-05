package com.example.ud08t00.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ud08t00.databinding.ItemLibroBinding
import com.example.ud08t00.model.Libro

class LibroAdapter(
    private var listaLibros: MutableList<Libro>,
    private val onClickListener: (Libro) -> Unit
) : RecyclerView.Adapter<LibroViewHolder>() {

    var position_pulsada: Int = -1
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val binding = ItemLibroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibroViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = listaLibros[position]
        holder.render(libro, onClickListener)

        holder.itemView.setOnLongClickListener {
            position_pulsada = holder.adapterPosition
            false
        }
    }

    override fun getItemCount(): Int = listaLibros.size

    fun actualizarLista(nuevaLista: List<Libro>) {
        listaLibros.clear()
        listaLibros.addAll(nuevaLista)
        notifyDataSetChanged()
    }

    fun eliminarLibro(position: Int) {
        if (position in 0 until listaLibros.size) {
            listaLibros.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun eliminarTodosLosLibros() {
        val itemCount = listaLibros.size
        if (itemCount > 0) {
            listaLibros.clear()
            notifyItemRangeRemoved(0, itemCount)
        }
    }

    fun editarTituloLibro(position: Int, nuevoTitulo: String) {
        if (position in 0 until listaLibros.size) {
            val libroActual = listaLibros[position]
            // Creamos un nuevo objeto Libro con el título cambiado
            listaLibros[position] = libroActual.copy(titulo = nuevoTitulo)
            notifyItemChanged(position)
        }
    }

    fun getLibroAt(position: Int): Libro? {
        return if (position in 0 until listaLibros.size) {
            listaLibros[position]
        } else {
            null
        }
    }
}
