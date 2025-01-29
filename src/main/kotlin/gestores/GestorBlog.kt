package org.es.blognoticias.gestores

import org.es.blognoticias.utils.Consola

class GestorBlog(
    private val gestorUsuarios: GestorUsuarios,
    private val gestorNoticias: GestorNoticias,
    private val gestorComentarios: GestorComentarios,
    private val consola: Consola
) {

    fun publicarNoticia(){
        try {
            // Elegir usuario
            val usuario = gestorUsuarios.getUsuario()

            // Comprobar que el estado es apto para publicar (UNBANNED_ACTIVO)
            gestorUsuarios.checkUsuarioEstado(usuario)

            // Obtener noticia
            val noticia = gestorNoticias.pedirDatosNoticia(autor = usuario._id)

            // Insertarla
            gestorNoticias.insertarNoticia(noticia)
        }catch (e:Exception){
            consola.imprimirMsj("ERROR - ${e.message}")
        }
    }


    fun escribirComentario(){
        try {
            // Elegir usuario
            val usuario = gestorUsuarios.getUsuario()

            // Elegir noticia
            val dateNoticia = gestorNoticias.getNoticiaDate()

            // Se obtiene los datos del cometario ya comprobados
            val comentario = gestorComentarios.pedirDatosCometario(usuario._id, dateNoticia)

            // Insertar el comentario
            gestorComentarios.insertarComentario(comentario)
        }catch (e:Exception){
            consola.imprimirMsj("ERROR - ${e.message}")
        }
    }


    fun registrarUsuario(){
        try {
            // Obtener usuario
            val usuario = gestorUsuarios.pedirDatosUsuario()

            // Insertarlo
            gestorUsuarios.insertarUsuario(usuario)
        }catch (e:Exception){
            consola.imprimirMsj("ERROR - ${e.message}")
        }

    }


    fun listarNoticiasDeUsuario(){
        try {
            val usuario = gestorUsuarios.getUsuario()
            gestorNoticias.listarNoticiasUsuario(usuario)

        }catch (e:Exception){
            consola.imprimirMsj("ERROR - ${e.message}")
        }
    }


    fun listarComentariosDeNoticia(){
        try {
            // Obtener la fecha de la noticia
            val dateNoticia = gestorNoticias.getNoticiaDate()

            // Filtro en la tabla de comentarios con esa fecha
            gestorComentarios.mostrarComentariosNoticia(dateNoticia)
        }catch (e: Exception){
            consola.imprimirMsj("ERROR - ${e.message}")
        }
    }


    fun buscarNoticiasPorTag(){
        try {
            gestorNoticias.getNoticiaByTag()
        }catch (e: Exception){
            consola.imprimirMsj("ERROR - ${e.message}")
        }
    }


    fun listarUltimasDiezNoticias(){
        gestorNoticias.listarUltimasDiezNoticias()
    }
}