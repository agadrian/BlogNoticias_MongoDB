package org.es.blognoticias.gestores

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.es.blognoticias.exceptions.UsuarioAlreadyExists
import org.es.blognoticias.exceptions.UsuarioinvalidoEx
import org.es.blognoticias.model.Direccion
import org.es.blognoticias.model.EstadoUsuario
import org.es.blognoticias.model.Usuario
import org.es.blognoticias.obtenerTodo
import org.es.blognoticias.utils.Consola

class GestorUsuarios(
    private val colUsuarios: MongoCollection<Usuario>,
    private val consola: Consola
) {

    /**
     * Devuelve la clave (id) del usuario elegido.
     * Si el email no existe, lanza excepcion.
     */
    fun getUsuario(): Usuario {
        colUsuarios.obtenerTodo().forEachIndexed { index, user ->
            consola.imprimirMsj("===== Usuario ${index+1} ===== $user\n")
        }
        val email = consola.pedirString("Introduce el email del usuario: ")

        // Comprobar que exista el usuario con ese email,
        if (!checkEmailExists(email)) throw UsuarioinvalidoEx("No existe ningun usuario con este email")
        val usuario = colUsuarios.find(Filters.eq("_id", email)).toList()

        // En este punto el usuario es único. Ya se comprueba en checkEmailExists
        return usuario[0]
    }


    fun pedirDatosUsuario(): Usuario {
        val _id = consola.pedirString("Introduce email de usuario: ")
        if (checkEmailExists(_id)) throw UsuarioAlreadyExists("Ya existe un usuario con este email.")

        val nombreCompleto = consola.pedirString("Introduce tu nombre completo: ")
        val userName = consola.pedirString("Introduce nombre de usuario: ")

        if (checkUsernameExists(userName)) throw UsuarioAlreadyExists("Ya existe un usuario con este nombre de usuario.")

        // TODO (Hacer telefonos en lista)
        val telefonos = consola.pedirListaStrings("Introduce telefono/s (en caso de varios, separados por coma (6660060006,6060066006)): ")

        return Usuario(
            _id,
            nombreCompleto,
            userName,
            direccion = Direccion(
                "calle",
                "num",
                "door",
                "324234",
                "cadi"
            ),
            telefonos = telefonos
        )
    }

    fun insertarUsuario(usuario: Usuario) {
        colUsuarios.insertOne(usuario)
        consola.imprimirMsj("Usuario registrado correctamente")
    }


    /**
     * Comprueba que exista algun usuario con el email pasado por parametro.
     * Se devuelve true si existe, false si no
     */
    private fun checkEmailExists(email: String): Boolean {
        val usuario = colUsuarios.find(Filters.eq("_id", email)).toList()
        return usuario.isNotEmpty()
    }


    /**
     * Comprueba que exista algun usuario con el username pasado por parametro.
     * Se devuelve true si existe, false si no
     */
    private fun checkUsernameExists(username: String): Boolean {
        val usuario = colUsuarios.find(Filters.eq("userName", username)).toList()
        return usuario.isNotEmpty()
    }


    /**
     * Comprueba el estado del usuario en el blog, lanzando excepcion si esta baneado o inactivo, mostrando infromacion de su estado.
     * Usado a la hora de publicar en el blog
     */
    fun checkUsuarioEstado(usuario: Usuario) {
        val estados = listOf( EstadoUsuario.BANNED_ACTIVO.name, EstadoUsuario.BANNED_INACTIVO.name, EstadoUsuario.UNBANNED_INACTIVO.name)
        val estadoUser = usuario.estado.name

        if(estadoUser in estados){
            throw UsuarioinvalidoEx("No se puede continuar. Este usuario está: $estadoUser")
        }
    }
}