package org.es.blognoticias.model

data class Usuario(
    val id: String,
    val nombreCompleto: String,
    val userName: String,
    val estado: String, // TODO("Ver si usar una enum class") // (banned / not banned, activo / inactivo).
    val direccion: Direccion, // Id de Direccion
    val telefonos: List<String>
)
