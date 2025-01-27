package org.es.blognoticias.model

/**
 * Enum class para representar los 4 tiupos de estados de un usuario del blog.
 * *** Para ingresar un nuevo usuario, usar usuario.estado.name
 */
enum class EstadoUsuario {
    BANNED_ACTIVO, // Permito que aunque este baneado pueda estar, aunque no pueda realizar ninguna accion en el blog
    BANNED_INACTIVO,
    UNBANNED_ACTIVO,
    UNBANNED_INACTIVO
}