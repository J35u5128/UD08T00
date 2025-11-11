package com.example.ud08t00.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ud08t00.databinding.ItemLibroBinding
import com.example.ud08t00.model.Libro

class LibroAdapter(
    private var listaLibros: MutableList<Libro>, // Esta será la lista que el adapter muestra y filtra
    private val onClickListener: (Libro) -> Unit
) : RecyclerView.Adapter<LibroViewHolder>() {

    // Variable para guardar la posición del ítem presionado en la lista visible
    var position_pulsada: Int = -1
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val binding = ItemLibroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibroViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = listaLibros[position]
        holder.render(libro, onClickListener)

        // Guardar la posición cuando se hace una pulsación larga
        holder.itemView.setOnLongClickListener {
            position_pulsada = holder.adapterPosition
            false // Devuelve false para que el menú contextual se muestre
        }
    }

    override fun getItemCount(): Int = listaLibros.size

    // --- MÉTODOS AÑADIDOS Y CORREGIDOS ---

    /**
     * Actualiza la lista que muestra el adaptador. Esencial para el buscador y la recarga.
     */
    fun actualizarLista(nuevaLista: List<Libro>) {
        listaLibros.clear()
        listaLibros.addAll(nuevaLista)
        notifyDataSetChanged() // Notifica al RecyclerView que los datos cambiaron masivamente
    }

    /**
     * Elimina un libro de la lista visible.
     */
    fun eliminarLibro(position: Int) {
        if (position in 0 until listaLibros.size) {
            listaLibros.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    /**
     * Edita el título de un libro en la lista visible.
     */
    fun editarTituloLibro(position: Int, nuevoTitulo: String) {
        if (position in 0 until listaLibros.size) {
            val libroActual = listaLibros[position]
            // Creamos un nuevo objeto Libro con el título cambiado
            listaLibros[position] = libroActual.copy(titulo = nuevoTitulo)
            notifyItemChanged(position)
        }
    }

    /**
     * Devuelve el objeto Libro en una posición específica de la lista visible.
     * Crucial para que el menú contextual funcione con la lista filtrada.
     */
    fun getLibroAt(position: Int): Libro? {
        return if (position in 0 until listaLibros.size) {
            listaLibros[position]
        } else {
            null
        }
    }
}
