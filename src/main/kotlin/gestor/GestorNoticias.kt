package org.es.blognoticias.gestor

import com.mongodb.client.MongoCollection
import org.bson.Document
import org.es.blognoticias.utils.Consola

class GestorNoticias(
    private val collection: MongoCollection<Document>,
    private val consola: Consola
) {


    fun publicarNoticia(){

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