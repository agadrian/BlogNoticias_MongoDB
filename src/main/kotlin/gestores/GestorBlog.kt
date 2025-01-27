package org.es.blognoticias.gestores

import com.mongodb.client.MongoCollection
import org.bson.Document
import org.es.blognoticias.utils.Consola

class GestorBlog(
    private val gestorUsuarios: GestorUsuarios,
    private val gestorNoticias: GestorNoticias,
    private val gestorComentarios: GestorComentarios,
    private val consola: Consola
) {


    fun publicarNoticia(){
        try {
            val emailUsuarioPublicador = gestorUsuarios.getUsuarioEmailOrNull()
            val docDatosNoticia = gestorNoticias.pedirDatosNoticia().append("autor", emailUsuarioPublicador)
            gestorNoticias.insertarNoticia(docDatosNoticia)

        }catch (e:Exception){
            consola.imprimirMsj("ERROR - ${e.message}")
        }
    }


    fun escribirComentario(){

    }


    fun registrarUsuario(){

    }


    fun listarNoticiasDeUsuario(){

    }


    fun listarComentariosDeNoticia(){

    }


    fun buscarNoticiasPorTag(){

    }


    fun listarUltimasDiezNoticias(){

    }











}