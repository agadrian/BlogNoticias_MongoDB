package org.es.blognoticias.exceptions

class UsuarioinvalidoEx(msg: String) : Exception(msg)
class UsuarioAlreadyExists(msg: String) : Exception(msg)
class FechaAlreadyExists(msg: String) : Exception(msg)
class FechaNotExists(msg: String) : Exception(msg)
class NotFoundException(msg: String) : Exception(msg)