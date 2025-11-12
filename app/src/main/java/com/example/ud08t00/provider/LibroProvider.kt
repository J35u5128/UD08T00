package com.example.ud08t00.provider

import com.example.ud08t00.model.Libro
import com.example.ud08t00.R

/**
 * Provee una lista estática de datos de libros para inicializar la BD o la lista inicial.
 */
class LibroProvider {
    companion object {
        fun cargarLista(): MutableList<Libro> {
            return mutableListOf(
                Libro(1, R.drawable.cien_anhos_de_soledad, "Cien años de soledad", "Gabriel García Márquez", 1967, "Novela", "9780307474728", "Relato mágico sobre la historia de la familia Buendía en el pueblo de Macondo."),
                Libro(2, R.drawable.don_quijote_de_la_mancha, "Don Quijote de la Mancha", "Miguel de Cervantes", 1605, "Novela", "9788491050529", "Las aventuras del hidalgo que pierde la cordura por leer libros de caballería."),
                Libro(3, R.drawable.el_hobbit, "El Hobbit", "J.R.R. Tolkien", 1937, "Fantasía", "9780618968633", "Historia de Bilbo Bolsón y su viaje lleno de peligros y maravillas."),
                Libro(4, R.drawable.orgullo_y_prejuicio, "Orgullo y prejuicio", "Jane Austen", 1813, "Romance", "9780141439518", "Relación amorosa entre Elizabeth Bennet y el aristócrata Mr. Darcy."),
                Libro(5, R.drawable.fahrenheit_451, "Fahrenheit 451", "Ray Bradbury", 1953, "Ciencia ficción", "9781451673319", "Mundo distópico donde los libros están prohibidos y se queman."),
                Libro(6, R.drawable.cronica_muerte_anunciada, "Crónica de una muerte anunciada", "Gabriel García Márquez", 1981, "Novela", "9788490622659", "La narración de un crimen anunciado en un pueblo pequeño."),
                Libro(7, R.drawable.el_nombre_del_viento, "El nombre del viento", "Patrick Rothfuss", 2007, "Fantasía", "9788401352836", "Historia del joven Kvothe, músico y mago, que recuerda su vida."),
                Libro(8, R.drawable.harry_potter, "Harry Potter y la piedra filosofal", "J.K. Rowling", 1997, "Fantasía", "9788478884452", "Comienzo de la saga de un joven mago en Hogwarts."),
                Libro(9, R.drawable.los_juegos_del_hambre, "Los juegos del hambre", "Suzanne Collins", 2008, "Distopía", "9780439023528", "Katniss Everdeen lucha por sobrevivir en un cruel reality show."),
                Libro(10, R.drawable.rebelion_granja, "Rebelión en la granja", "George Orwell", 1945, "Satírico", "9788499890943", "Fábula política sobre la corrupción y el poder en una granja."),
                Libro(11, R.drawable.matar_ruisenor, "Matar a un ruiseñor", "Harper Lee", 1960, "Novela", "9780061120084", "Relato sobre la injusticia racial en el sur de Estados Unidos."),
                Libro(12, R.drawable.a1984, "1984", "George Orwell", 1949, "Distopía", "9780451524935", "Sociedad totalitaria controlada por el Gran Hermano."),
                Libro(13, R.drawable.gran_gatsby, "El gran Gatsby", "F. Scott Fitzgerald", 1925, "Novela", "9780743273565", "Crónica de la decadencia y sueños rotos en la América de los años 20."),
                Libro(14, R.drawable.la_sombra_del_viento, "La sombra del viento", "Carlos Ruiz Zafón", 2001, "Misterio", "9788490626343", "Un joven descubre un libro que cambiará su vida en la Barcelona de posguerra."),
                Libro(15, R.drawable.cumbres_borrascosas, "Cumbres borrascosas", "Emily Brontë", 1847, "Romance", "9781853260018", "Historia de amor y venganza en los páramos ingleses."),
                Libro(16, R.drawable.codigo_da_vinci, "El código Da Vinci", "Dan Brown", 2003, "Thriller", "9780307474274", "Misterio y conspiración en torno a secretos religiosos en Europa."),
                Libro(17, R.drawable.el_principito, "El principito", "Antoine de Saint-Exupéry", 1943, "Literatura infantil", "9780156012195", "Un pequeño príncipe narra sus aventuras y enseñanzas en el universo."),
                Libro(18, R.drawable.la_ladrona_de_libros, "La ladrona de libros", "Markus Zusak", 2005, "Novela histórica", "9780375842207", "Una niña roba libros en la Alemania nazi para sobrevivir."),
                Libro(19, R.drawable.siddhartha, "Siddhartha", "Hermann Hesse", 1922, "Filosofía", "9780142437186", "Búsqueda espiritual y autoconocimiento en tiempos de Buda."),
                Libro(20, R.drawable.cronica_del_pajaro, "Crónica del pájaro que da cuerda al mundo", "Haruki Murakami", 1994, "Realismo mágico", "9788432218025", "Compleja novela que mezcla lo real y lo fantástico en Japón.")
            )
        }
    }
}