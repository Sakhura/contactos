package com.sakhura.contactos.viewmodel


import androidx.lifecycle.LiveData
import com.sakhura.contactos.database.ContactosDatabase
import com.sakhura.contactos.model.Categoria
import com.sakhura.contactos.repository.ContactosRepository

class ContactosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContactosRepository

    val contactos: LiveData<List<Contacto>>
    val categorias: LiveData<List<Categoria>>
    val grupos: LiveData<List<Grupo>>

    init {
        val db = ContactosDatabase.getDatabase(application)
        repository = ContactosRepository(
            db.contactoDao(),
            db.categoriaDao(),
            db.grupoDao()
        )
        contactos = repository.contactos
        categorias = repository.categorias
        grupos = repository.grupos
    }

    // --- Grupos ---
    fun insertarGrupo(grupo: Grupo) = viewModelScope.launch {
        repository.insertarGrupo(grupo)
    }

    fun asociarContactoAGrupo(contactoId: Int, grupoId: Int) = viewModelScope.launch {
        repository.asociarContactoAGrupo(ContactoGrupoCrossRef(contactoId, grupoId))
    }

    fun obtenerContactosDeGrupo(grupoId: Int): LiveData<GrupoConContactos> {
        return repository.obtenerGrupoConContactos(grupoId)
    }

    // --- Contactos (resumen) ---
    fun insertar(contacto: Contacto) = viewModelScope.launch {
        repository.insertarContacto(contacto)
    }

    fun eliminar(contacto: Contacto) = viewModelScope.launch {
        repository.eliminarContacto(contacto)
    }

    fun actualizar(contacto: Contacto) = viewModelScope.launch {
        repository.actualizarContacto(contacto)
    }

    fun buscar(query: String) {
        _busqueda.value = query
    }

    private val _busqueda = MutableLiveData<String>()
    val contactosFiltrados: LiveData<List<Contacto>> = Transformations.switchMap(_busqueda) {
        if (it.isBlank()) contactos else repository.buscarContactos(it)
    }
}
