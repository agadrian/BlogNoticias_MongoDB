package org.es.blognoticias.model

import org.bson.codecs.pojo.annotations.BsonId

data class Usuario(
    @BsonId
    val _id: String,
    val nombreCompleto: String,
    val userName: String,
    val estado: EstadoUsuario = EstadoUsuario.UNBANNED_ACTIVO,
    val direccion: Direccion, // Id de Direccion
    val telefonos: List<String>
){
    override fun toString(): String {
        return "\n- Email: $_id\n- Nombre completo: $nombreCompleto\n- Nombre usuario: $userName\n- Estado cuenta: $estado\n- Dirección: $direccion\n- Teléfonos: ${telefonos.joinToString(", ") {it}})"
    }
}



