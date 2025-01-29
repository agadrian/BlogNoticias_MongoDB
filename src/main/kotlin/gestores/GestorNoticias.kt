package org.es.blognoticias.gestores

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.es.blognoticias.exceptions.FechaAlreadyExists
import org.es.blognoticias.exceptions.FechaNotExists
import org.es.blognoticias.exceptions.NotFoundException
import org.es.blognoticias.model.Noticia
import org.es.blognoticias.model.Usuario
import org.es.blognoticias.obtenerTodo
import org.es.blognoticias.utils.Consola
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GestorNoticias(
    private val colNoticias: MongoCollection<Noticia>,
    private val consola: Consola
) {

    /**
     * Pide los datos de una noticia. Por defecto ya tiene la referencia al usuario; se pasa por parametro.
     * La fecha de publicación de una noticia debe ser única y no editable, por lo que se comprueba previamente si existe o no.
     * En caso de que exista o algun otro error, se lanza excepcion personalizada.
     */
    fun pedirDatosNoticia(autor: String): Noticia{
        consola.imprimirMsj("** INTRODUCE DATOS NOTICIA **")

        val titulo = consola.pedirString("Introduce el título de la noticia: ")
        val cuerpo = consola.pedirString("Introduce el cuerpo de la noticia: ")
        val fechaPub = consola.pedirFecha("Introduce la fecha de publicaciión de la noticia (vacía para el día de hoy): ")
        if (checkFechaExists(fechaPub)){
            throw FechaAlreadyExists("Esta fecha de publicación ya existe.")
        }
        val tags = consola.pedirListaStrings("Introduce tag/s (en caso de varios, separados por coma: (tag1,tag2,tag3...)). Enter para dejarlo vacío: ")

        return Noticia(titulo, cuerpo, fechaPub, autor, tags)
    }

    /**
     * Inserta la noticia que recibe por parametro
     */
    fun insertarNoticia(noticia: Noticia) {
        colNoticias.insertOne(noticia)
        consola.imprimirMsj("Noticia insertada correctamente.")
    }


    /**
     * Obtener la fecha de una noticia, que es su identificador unico, o lanza excpecion si no existe ninguna noticia con esa fecha
     */
    fun getNoticiaDate(): String{
        colNoticias.obtenerTodo().forEachIndexed { index, noticia ->
            consola.imprimirMsj("===== Noticia ${index + 1} ===== $noticia\n")
        }
        val date = consola.pedirFecha("Introduce la fecha de la noticia (dd/mm/yyyy): ")
        return if (checkFechaExists(date)) date else throw FechaNotExists("La fecha seleccionada no corresponde con ninguna noticia.")
    }


    /**
     * Comprobar si alguna noticia tiene como fecha la pasada por parametro
     */
    private fun checkFechaExists(fecha: String): Boolean{
        val noticia = colNoticias.find(Filters.eq("fechaPub", fecha)).firstOrNull()
        return noticia != null
    }




    fun listarNoticiasUsuario(usuario: Usuario){
        val noticias = getNoticiasByUser(usuario)
        noticias.forEachIndexed {index, noticia ->
            consola.imprimirMsj("\n===== Noticia ${index+1} ===== $noticia")
        }
    }


    private fun getNoticiasByUser(usuario: Usuario): List<Noticia>{
        val noticias = colNoticias.find(Filters.eq("autor", usuario._id)).toList()
        if (noticias.isEmpty()) throw NotFoundException("No se ha encontrado ninguna noticia de este autor")
        return noticias
    }


    fun getNoticiaByTag(){
        val tag = consola.pedirString("Introduce tag: ")
        val noticias = colNoticias.find(Filters.eq("tags", tag)).toList().ifEmpty { throw NotFoundException("No se ha encontrado noticias con el tag '$tag'") }

        noticias.forEachIndexed { index, noticia ->
            consola.imprimirMsj("\n===== Noticia ${index+1} ===== $noticia")
        }
    }


    /**
     * TODO: Comentar en el pdf que lo hago asi para que salga con el formato que quiero, tanto en codigo como en la BD, aunque sea un poco menos eficiente.
     * TODO: La forma eficiente seria usando Date a la hora de introducir y obtener registros en Mongo.
     * Muestra por consola las 10 ultimas noticias
     */
    fun listarUltimasDiezNoticias(){
        colNoticias.find().sortedByDescending {
            LocalDate.parse(it.fechaPub, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }.take(10).forEachIndexed { index, noticia ->
            consola.imprimirMsj("\n===== Noticia ${index+1} ===== $noticia")
        }
    }



}