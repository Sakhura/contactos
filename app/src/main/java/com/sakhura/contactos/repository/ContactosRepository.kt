package com.sakhura.contactos.repository

class ContactosRepository(
    private val contactoDao: ContactoDao,
    private val categoriaDao: CategoriaDao,
    private val grupoDao: GrupoDao
) {

    val contactos = contactoDao.obtenerContactos()
    val categorias = categoriaDao.obtenerCategorias()
    val grupos = grupoDao.obtenerGrupos()

    // --- CRUD Contactos ---
    suspend fun insertarContacto(contacto: Contacto) = contactoDao.insertar(contacto)
    suspend fun actualizarContacto(contacto: Contacto) = contactoDao.actualizar(contacto)
    suspend fun eliminarContacto(contacto: Contacto) = contactoDao.eliminar(contacto)

    // --- CRUD Categorías ---
    suspend fun insertarCategoria(categoria: Categoria) = categoriaDao.insertar(categoria)

    // --- Buscar contactos ---
    fun buscarContactos(query: String) = contactoDao.buscarContactos("%$query%")

    // --- CRUD Grupos ---
    suspend fun insertarGrupo(grupo: Grupo) = grupoDao.insertarGrupo(grupo)
    suspend fun asociarContactoAGrupo(crossRef: ContactoGrupoCrossRef) =
        grupoDao.insertarContactoGrupoCrossRef(crossRef)
    fun obtenerGrupoConContactos(grupoId: Int) =
        grupoDao.obtenerGrupoConContactos(grupoId)
}
