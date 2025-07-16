package com.sakhura.contactos.utils


import android.content.Context
import com.tuapp.contactos.model.Contacto
import java.io.File

object BackupUtils {

    fun exportar(context: Context, contactos: List<Contacto>) {
        val file = File(context.getExternalFilesDir(null), "backup_contactos.txt")
        file.writeText(contactos.joinToString("\n") {
            "${it.nombre},${it.telefono},${it.email}"
        })
    }

    fun importar(context: Context): List<Contacto> {
        val file = File(context.getExternalFilesDir(null), "backup_contactos.txt")
        if (!file.exists()) return emptyList()

        return file.readLines().mapNotNull {
            val datos = it.split(",")
            if (datos.size == 3) {
                Contacto(nombre = datos[0], telefono = datos[1], email = datos[2], categoriaId = 1)
            } else null
        }
    }

    fun exportarA_vCard(context: Context, contacto: Contacto): File {
        val vcf = """
            BEGIN:VCARD
            VERSION:3.0
            FN:${contacto.nombre}
            TEL:${contacto.telefono}
            EMAIL:${contacto.email}
            END:VCARD
        """.trimIndent()
