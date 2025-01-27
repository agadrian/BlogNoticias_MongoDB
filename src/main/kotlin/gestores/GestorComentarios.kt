package org.es.blognoticias.gestores

import com.mongodb.client.MongoCollection
import org.bson.Document
import org.es.blognoticias.utils.Consola

class GestorComentarios(
    private val colComentarios: MongoCollection<Document>,
    private val consola: Consola
) {

}