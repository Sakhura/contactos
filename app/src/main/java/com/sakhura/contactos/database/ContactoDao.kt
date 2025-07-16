package com.sakhura.contactos.database

@Dao
interface ContactoDao {
    @Query("SELECT * FROM contactos ORDER BY nombre ASC")
    fun obtenerContactos(): LiveData<List<Contacto>>

    @Query("SELECT * FROM contactos WHERE nombre LIKE :query")
    fun buscarContactos(query: String): LiveData<List<Contacto>>

    @Insert suspend fun insertar(contacto: Contacto)
    @Update suspend fun actualizar(contacto: Contacto)
    @Delete suspend fun eliminar(contacto: Contacto)
}
