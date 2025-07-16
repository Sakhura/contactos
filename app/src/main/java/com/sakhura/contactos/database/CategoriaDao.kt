package com.sakhura.contactos.database

@Dao
interface CategoriaDao {
    @Query("SELECT * FROM categorias")
    fun obtenerCategorias(): LiveData<List<Categoria>>

    @Insert suspend fun insertar(categoria: Categoria)
}
