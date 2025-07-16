package com.sakhura.contactos.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sakhura.contactos.model.*

@Dao
interface GrupoDao {

    // CRUD para grupos
    @Insert suspend fun insertarGrupo(grupo: Grupo)
    @Update suspend fun actualizarGrupo(grupo: Grupo)
    @Delete suspend fun eliminarGrupo(grupo: Grupo)
    @Query("SELECT * FROM grupos ORDER BY nombre ASC")
    fun obtenerGrupos(): LiveData<List<Grupo>>

    // Relación contacto-grupo
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarContactoGrupoCrossRef(ref: ContactoGrupoCrossRef)

    @Delete suspend fun eliminarContactoGrupoCrossRef(ref: ContactoGrupoCrossRef)

    // Obtener grupo con sus contactos
    @Transaction
    @Query("SELECT * FROM grupos WHERE id = :grupoId")
    fun obtenerGrupoConContactos(grupoId: Int): LiveData<GrupoConContactos>
}
