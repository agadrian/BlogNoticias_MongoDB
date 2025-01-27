package org.es.blognoticias.gestores

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.bson.Document
import org.es.blognoticias.exceptions.FechaAlreadyExists
import org.es.blognoticias.utils.Consola

class GestorNoticias(
    private val colNoticias: MongoCollection<Document>,
    private val consola: Consola
) {

    /**
     * La fecha de publicación de una noticia debe ser única y no editable.
     */
    fun pedirDatosNoticia(): Document{
        consola.imprimirMsj("** INTRODUCE DATOS NOTICIA **")

        val titulo = consola.pedirString("Introduce el título de la noticia: ")
        val cuerpo = consola.pedirString("Introduce el cuerpo de la noticia: ")
        // Check fecha, debe ser unica
        val fechaPub = consola.pedirFecha("Introduce la fecha de publicaciión de la noticia (vacía para el día de hoy): ")
        if (checkFechaExists(fechaPub)){
            throw FechaAlreadyExists("Esta fecha de publicación ya existe.")
        }


        val tags = consola.pedirString("Introduce etiqueta/s (en caso de varias, separadas por comas sin espacios): ")


        // No pido el autor porque le hare append al seleccionado previamente del listado de usuarios
        return Document()
            .append("titulo", titulo)
            .append("cuerpo", cuerpo)
            .append("fechaPub", fechaPub)
            .append("tags", tags)
    }



    fun insertarNoticia(docDatosNoticia: Document) {
        colNoticias.insertOne(docDatosNoticia)
        consola.imprimirMsj("Noticia insertada correctamente.")
    }

    private fun checkFechaExists(fecha: String): Boolean{
        val noticia = colNoticias.find(Filters.eq("fechaPub", fecha)).firstOrNull()
        return noticia != null
    }
}