package org.es.blognoticias.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Consola {

    fun imprimirMsj(msj: String, newLine: Boolean = true){
        if (newLine) println(msj) else print(msj)
    }



    fun pedirString(msg: String): String{
        imprimirMsj(msg, false)

        var str: String
        do {
            str = readln()
            if (str.isBlank()){
                imprimirMsj("Debes introducir algun valor: ", false)
            }
        }while (str.isBlank())
        return str
    }

    fun pedirListaStrings(msg: String): List<String>{
        imprimirMsj(msg, false)

        val input = readln().trim()

        return if (input.isBlank()){
            emptyList()
        }else {
            // Seprar por coma y quitar espacios
            input.split(",").map { it.trim() }
        }
    }

    fun pedirInt(msg: String): Int{
        imprimirMsj(msg, false)
        var int: Int?

        do {
            int = readln().toIntOrNull()
            if (int == null || int <= 0){
                imprimirMsj("Debes introducir un entero positivo: ", false)
            }
        }while (int == null || int <= 0)
        return int
    }

    fun pedirDouble(msg: String): Double{
        imprimirMsj(msg, false)
        var double: Double?

        do {
            double = readln().toDoubleOrNull()
            if (double == null){
                imprimirMsj("Debes introducir un valor numerico positivo: ", false)
            }
        }while (double == null ||double <= 0.0f)
        return double
    }

    /**
     * Fecha con formato dd/mm/aaaa
     */
    fun pedirFecha(msg: String): String{
        imprimirMsj(msg, false)

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        var fechaValida: LocalDate? = null
        var fechaIntroducida: String

        do {
            fechaIntroducida = readln()

            // Si en blanco, fecha actual formateada
            if (fechaIntroducida.isBlank()){
                return LocalDate.now().format(formatter)
            }

            try {
                fechaValida = LocalDate.parse(fechaIntroducida, formatter)
            }catch (e: Exception){
                imprimirMsj("Formato de fecha inválido, introduce una fecha válida (dd/mm/aaaa): ", false)
            }
        }while (fechaValida == null)

        return fechaValida.format(formatter)
    }


}