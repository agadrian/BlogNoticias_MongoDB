package org.es.blognoticias.model

import java.time.LocalDateTime


data class Comentarios(
    val usuario: String, // Id del Usuario
    val noticia: String, // Id de Noticia
    val texto: String,
    val fechaHora: LocalDateTime,
)
