package org.es.blognoticias.model

data class Noticia(
    val titulo: String,
    val cuerpo: String,
    val fechaPub: String,
    val autor: String,
    val tags: List<String>
){
    override fun toString(): String {
        return "\n- Título: $titulo\n- Cuerpo: $cuerpo\n- Autor: $autor\n- Fecha publicación: $fechaPub\n- Tags: \n   - ${tags.joinToString("\n   - ") {it}}"
    }
}
