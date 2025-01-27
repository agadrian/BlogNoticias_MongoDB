package org.es.blognoticias.gestores

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.bson.Document
import org.es.blognoticias.exceptions.UsuarioinvalidoEx
import org.es.blognoticias.model.EstadoUsuario
import org.es.blognoticias.obtenerTodo
import org.es.blognoticias.utils.Consola

class GestorUsuarios(
    private val colUsuarios: MongoCollection<Document>,
    private val consola: Consola
) {

    /**
     * Devuelve la clave (id) del uusuario elegido
     */
    fun getUsuarioEmailOrNull(): String {

        colUsuarios.obtenerTodo().forEachIndexed { index, user ->
            consola.imprimirMsj("${index+1} - $user")
        }

        val email = consola.pedirString("Introduce el email del usuario que la va a publicar: ")
        checkUsuarioEmailEstado(email)
        return email
    }


    private fun checkUsuarioEmailEstado(email: String) {
        val usuario = colUsuarios.find(Filters.eq("_id", email)).toList()

        if (usuario.isEmpty()) {
            throw UsuarioinvalidoEx("Este email de usuario no existe.")
        }

        val estados = listOf( EstadoUsuario.BANNED_ACTIVO.name, EstadoUsuario.BANNED_INACTIVO.name, EstadoUsuario.UNBANNED_INACTIVO.name)
        val estadoUser = usuario[0].getString("estado")

        if(estadoUser in estados){
            throw UsuarioinvalidoEx("No se puede continuar con la creación de una noticia. Este usuario está: $estadoUser")
        }
    }
}