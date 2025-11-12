package com.example.ud08t00.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.ud08t00.R
import com.example.ud08t00.adapter.LibroAdapter
import com.example.ud08t00.dao.LibroDAO
import com.example.ud08t00.model.Libro
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var libroDAO: LibroDAO
    private lateinit var listaMaestraDeLibros: MutableList<Libro>
    private lateinit var adapter: LibroAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var searchView: SearchView
    private lateinit var fabDeleteAll: FloatingActionButton

    private val editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val nuevoTitulo = result.data?.getStringExtra("EXTRA_NUEVO_TITULO")
            if (nuevoTitulo != null) {
                val position = adapter.position_pulsada
                adapter.editarTituloLibro(position, nuevoTitulo)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        libroDAO = LibroDAO(this)
        listaMaestraDeLibros = libroDAO.getAllLibros()

        recyclerView = findViewById(R.id.rvLibros)
        swipeRefreshLayout = findViewById(R.id.srlDatos)
        searchView = findViewById(R.id.searchView)
        fabDeleteAll = findViewById(R.id.fab_delete_all)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = LibroAdapter(listaMaestraDeLibros.toMutableList()) { libro ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_LIBRO", libro)
            }
            startActivity(intent)
        }

        recyclerView.adapter = adapter
        registerForContextMenu(recyclerView)

        swipeRefreshLayout.setOnRefreshListener { recargarDatos() }
        setupSearchView()

        fabDeleteAll.setOnClickListener {
            mostrarDialogoDeBorradoTotal()
        }
    }

    private fun mostrarDialogoDeBorradoTotal() {
        AlertDialog.Builder(this)
            .setTitle("Eliminar Todo")
            .setMessage("¿Estás seguro de que quieres eliminar todos los libros? Esta acción no se puede deshacer.")
            .setPositiveButton("Aceptar") { _, _ ->
                adapter.eliminarTodosLosLibros()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun recargarDatos() {
        listaMaestraDeLibros = libroDAO.getAllLibros()
        adapter.actualizarLista(listaMaestraDeLibros)
        searchView.setQuery("", false)
        searchView.clearFocus()
        swipeRefreshLayout.isRefreshing = false
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val query = newText.orEmpty().trim().lowercase(Locale.getDefault())

                val librosFiltrados = if (query.isEmpty()) {
                    listaMaestraDeLibros
                } else {
                    listaMaestraDeLibros.filter { libro ->
                        libro.titulo.lowercase(Locale.getDefault()).contains(query) ||
                                libro.autor.lowercase(Locale.getDefault()).contains(query)
                    }
                }
                adapter.actualizarLista(librosFiltrados)
                return true
            }
        })
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = adapter.position_pulsada
        if (position == -1) return super.onContextItemSelected(item)

        val libroSeleccionado = adapter.getLibroAt(position) ?: return super.onContextItemSelected(item)

        return when (item.itemId) {
            R.id.context_edit -> {
                val intent = Intent(this, EditActivity::class.java).apply {
                    putExtra("EXTRA_TITULO", libroSeleccionado.titulo)
                    putExtra("EXTRA_IMAGEN_ID", libroSeleccionado.imagenResId)
                }
                editLauncher.launch(intent)
                true
            }
            R.id.context_delete -> {
                AlertDialog.Builder(this)
                    .setTitle("Eliminar Libro")
                    .setMessage("¿Estás seguro de que quieres eliminar '${libroSeleccionado.titulo}'?")
                    .setPositiveButton("Aceptar") { _, _ -> adapter.eliminarLibro(position) }
                    .setNegativeButton("Cerrar", null)
                    .show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}