package com.sakhura.contactos.viewmodel

import kotlin.collections.get

class AgregarContactoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgregarContactoBinding
    private lateinit var viewModel: ContactosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarContactoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ContactosViewModel::class.java]

        binding.btnGuardar.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val telefono = binding.etTelefono.text.toString()
            val email = binding.etEmail.text.toString()

            if (ValidationUtils.validarFormulario(nombre, telefono, email)) {
                val contacto = Contacto(nombre = nombre, telefono = telefono, email = email, categoriaId = 1)
                viewModel.insertar(contacto)
                Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Verifica los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}