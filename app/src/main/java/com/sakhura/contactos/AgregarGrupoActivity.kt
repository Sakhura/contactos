package com.sakhura.contactos

class AgregarGrupoActivity : AppCompatActivity() {

    private lateinit var viewModel: ContactosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_grupo)

        viewModel = ViewModelProvider(this)[ContactosViewModel::class.java]

        val etNombreGrupo = findViewById<EditText>(R.id.etNombreGrupo)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarGrupo)

        btnGuardar.setOnClickListener {
            val nombre = etNombreGrupo.text.toString()
            if (nombre.isNotBlank()) {
                viewModel.insertarGrupo(Grupo(nombre = nombre))
                Toast.makeText(this, "Grupo creado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Nombre vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
