package com.sakhura.contactos.model

@Entity(tableName = "categorias")
data class Categoria(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String
)
