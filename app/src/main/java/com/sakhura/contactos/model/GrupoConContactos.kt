package com.sakhura.contactos.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class GrupoConContactos(
    @Embedded val grupo: Grupo,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(ContactoGrupoCrossRef::class)
    )
    val contactos: List<Contacto>
)
