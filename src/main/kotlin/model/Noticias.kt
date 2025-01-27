package org.es.blognoticias.model

import java.time.LocalDateTime


data class Noticias(
    val titulo: String,
    val cuerpo: String,
    val fechaPub: LocalDateTime,
    val autor: String,
    val tags: List<String>
)
