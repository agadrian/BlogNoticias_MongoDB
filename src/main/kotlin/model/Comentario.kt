package org.es.blognoticias.model

data class Comentario(
    val usuario: String, // Id del Usuario (email)
    val noticia: String, // Id de Noticia (fecha)
    val texto: String,
    val fechaHora: String,
){
    override fun toString(): String {
        return "\nEmail usuario: $usuario\n- Fecha noticia: $noticia\n- Texto: $texto\n- Fecha y hora publicación: $fechaHora\n"
    }
}
