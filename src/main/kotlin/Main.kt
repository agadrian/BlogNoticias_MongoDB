package org.es.blognoticias

import org.es.blognoticias.dbConnection.MongoDB
import org.es.blognoticias.gestor.GestorNoticias
import org.es.blognoticias.utils.Consola
import org.es.blognoticias.utils.Menu

fun main() {

    val consola = Consola()

    try {
        val collection = MongoDB.database.getCollection("blognoticias")
        val gestorNoticias = GestorNoticias(collection, consola)

        val menu = Menu(gestorNoticias, consola)

        menu.menuPrincipal()
    }catch (e: Exception){
        println("ERROR - ${e.message}")
        MongoDB.cerrarConexion()
    }


}