package org.es.blognoticias.model


data class Direccion(
    val calle: String,
    val numero: String,
    val puerta: String,
    val cp: String,
    val ciudad: String,
){

    override fun toString(): String {
        return "\n   - Calle: $calle\n   - Núm: $numero\n   - CP: $cp\n   - Ciudad: $ciudad"
    }
}
