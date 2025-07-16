package com.sakhura.contactos

class AsignarContactosGrupoActivity : AppCompatActivity() {

    private lateinit var viewModel: ContactosViewModel
    private var grupoId: Int = 0
    private val contactosSeleccionados = mutableSetOf<Contacto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asignar_contactos)

        grupoId = intent.getIntExtra("grupoId", 0)
        viewModel = ViewModelProvider(this)[ContactosViewModel::class.java]

        val rv = findViewById<RecyclerView>(R.id.rvContactosGrupo)
        rv.layoutManager = LinearLayoutManager(this)

        viewModel.contactos.observe(this) { lista ->
            val adapter = ContactosGrupoAdapter(lista) { contacto, seleccionado ->
                if (seleccionado) contactosSeleccionados.add(contacto)
                else contactosSeleccionados.remove(contacto)
            }
            rv.adapter = adapter
        }

        // Por ejemplo, botón guardar
        val btnGuardar = Button(this).apply {
            text = "Guardar"
            setOnClickListener {
                contactosSeleccionados.forEach {
                    viewModel.asociarContactoAGrupo(it.id, grupoId)
                }
                Toast.makeText(this@AsignarContactosGrupoActivity, "Contactos asignados", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        (findViewById<LinearLayout>(R.id.rvContactosGrupo).parent as LinearLayout).addView(btnGuardar)
    }
}
