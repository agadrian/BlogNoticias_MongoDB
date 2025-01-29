package org.es.blognoticias.gestores

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.es.blognoticias.exceptions.NotFoundException
import org.es.blognoticias.model.Comentario
import org.es.blognoticias.utils.Consola
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GestorComentarios(
    private val colComentarios: MongoCollection<Comentario>,
    private val consola: Consola
) {

    /**
     * Pedir los datos necesarios para crear un comentario. Ya se le pasa la referencia al autor y a la notcia.
     */
    fun pedirDatosCometario(idAutor: String, idNoticia: String): Comentario{
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        val fechaHora = LocalDateTime.now().format(formatter)

        val texto = consola.pedirString("Introduce el contenido del comentario: ")


        return Comentario(
            idAutor,
            idNoticia,
            texto,
            fechaHora
        )
    }

    /**
     * Inserta el comentario que recibe por parametro
     */
    fun insertarComentario(comentario: Comentario) {
        colComentarios.insertOne(comentario)
        consola.imprimirMsj("Comentario insertado correctamente por el usuario con email: ${comentario.usuario} en la noticia con fecha: ${comentario.noticia}")
    }


    /**
     * Muestra por pantalla la lista de comentarios que tiene una noticia en concreto.
     * Lanmza excepcion si la noticia no tiene comentarios
     */
    fun mostrarComentariosNoticia(dateNoticia: String) {
        val comentarios = colComentarios.find(Filters.eq("noticia", dateNoticia)).toList()
        if (comentarios.isEmpty()) throw NotFoundException("No se ha encontrado comentarios en la noticia con fecha $dateNoticia")

        consola.imprimirMsj("=== Comentarios de la noticia con fecha $dateNoticia ===\n")
        comentarios.forEachIndexed { index, com ->
            consola.imprimirMsj("*** Comentario ${index + 1} *** $com\n")
        }
    }


}