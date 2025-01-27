package org.es.blognoticias.utils

import org.es.blognoticias.dbConnection.MongoDB
import org.es.blognoticias.gestores.GestorBlog


/**
 * Clase que representa el menú principal de la aplicación.
 */
class Menu(
    private val gestorBlog: GestorBlog,
    private val consola: Consola
) {
    /**
     * Función interna para pedir una opción dentro de un rango específico.
     * @param min El valor mínimo permitido.
     * @param max El valor máximo permitido.
     * @return La opción seleccionada por el usuario.
     */
    private fun pedirOpcion(min: Int, max: Int): Int {
        var opcion: Int

        do {
            print("Introduce opción -> ")
            opcion = readln().toIntOrNull() ?: -1

            if (opcion == -1) {
                consola.imprimirMsj("Opción no válida. ")
            } else if (opcion !in min..max) {
                consola.imprimirMsj("Opción fuera de rango. ")
            }
        } while (opcion !in min..max)
        return opcion
    }


    /**
     * Función que muestra y gestiona el menú principal.
     */
    fun menuPrincipal() {

        var opc: Int
        do {
            imprimirMenuPrincipal()
            opc = pedirOpcion(1, 8)

            when (opc) {
                1 -> gestorBlog.publicarNoticia()
                2 -> gestorBlog.escribirComentario()
                3 -> gestorBlog.registrarUsuario()
                4 -> gestorBlog.listarNoticiasDeUsuario()
                5 -> gestorBlog.listarComentariosDeNoticia()
                6 -> gestorBlog.buscarNoticiasPorTag()
                7 -> gestorBlog.listarUltimasDiezNoticias()
                8 -> {
                    consola.imprimirMsj("Saliendo del programa...")
                    MongoDB.cerrarConexion()
                }
            }
        } while (opc != 8)
    }


    /**
     * Función interna para imprimir las opciones del menú principal.
     */
    private fun imprimirMenuPrincipal() {
        consola.imprimirMsj("\nMenu ejercicios:")
        consola.imprimirMsj("1.- Publicar noticia")
        consola.imprimirMsj("2.- Escribir comentario")
        consola.imprimirMsj("3.- Registrar usuario")
        consola.imprimirMsj("4.- Listar noticias de un usuario")
        consola.imprimirMsj("5.- Listar comentarios de una noticia")
        consola.imprimirMsj("6.- Buscar noticias por etiquetas")
        consola.imprimirMsj("7.- Listar ultimas 10 noticias")
        consola.imprimirMsj("8.- Salir")
    }
}