package com.sakhura.contactos.database

import com.sakhura.contactos.model.Categoria

@Database(
    entities = [Contacto::class, Categoria::class, Grupo::class, ContactoGrupoCrossRef::class],
    version = 1
)
abstract class ContactosDatabase : RoomDatabase() {

    abstract fun contactoDao(): ContactoDao
    abstract fun categoriaDao(): CategoriaDao
    abstract fun grupoDao(): GrupoDao  // ⬅️ nuevo DAO

    companion object {
        @Volatile private var INSTANCE: ContactosDatabase? = null

        fun getDatabase(context: Context): ContactosDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactosDatabase::class.java,
                    "contactos_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
