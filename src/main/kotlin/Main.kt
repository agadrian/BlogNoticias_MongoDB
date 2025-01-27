package org.es.blognoticias

import com.mongodb.client.MongoCollection
import org.bson.Document
import org.es.blognoticias.dbConnection.MongoDB
import org.es.blognoticias.gestores.GestorBlog
import org.es.blognoticias.gestores.GestorComentarios
import org.es.blognoticias.gestores.GestorNoticias
import org.es.blognoticias.gestores.GestorUsuarios

import org.es.blognoticias.utils.Consola
import org.es.blognoticias.utils.Menu

fun main() {

    val consola = Consola()

    try {
        val collectionUsuarios = MongoDB.database.getCollection("usuarios")
        val collectionNoticias = MongoDB.database.getCollection("noticias")
        val collectionComentarios = MongoDB.database.getCollection("comentarios")

        val gestorUsuarios = GestorUsuarios(collectionUsuarios, consola)
        val gestorNoticias = GestorNoticias(collectionNoticias, consola)
        val gestorComentarios = GestorComentarios(collectionComentarios, consola)

        val gestorBlog = GestorBlog(
            gestorUsuarios,
            gestorNoticias,
            gestorComentarios,
            consola
        )

        val menu = Menu(gestorBlog, consola)

        menu.menuPrincipal()
    }catch (e: Exception){
        println("ERROR - ${e.message}")
        MongoDB.cerrarConexion()
    }
}


fun MongoCollection<Document>.obtenerTodo(): List<Document>{
    return this.find().toList()
}