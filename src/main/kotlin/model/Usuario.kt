package org.es.blognoticias.model

data class Usuario(
    val _id: String,
    val nombreCompleto: String,
    val userName: String,
    val estado: EstadoUsuario,
    val direccion: Direccion, // Id de Direccion
    val telefonos: List<String>
)
