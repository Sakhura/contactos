package com.sakhura.contactos.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sakhura.contactos.model.Categoria

@Dao
interface CategoriaDao {
    @Query("SELECT * FROM categorias")
    fun obtenerCategorias(): LiveData<List<Categoria>>

    @Insert
    suspend fun insertar(categoria: Categoria)
}
