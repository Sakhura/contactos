package com.sakhura.contactos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val spinner = findViewById<Spinner>(R.id.spinnerGrupos)
        viewModel.grupos.observe(this) { grupos ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, grupos.map { it.nombre })
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    val grupoSeleccionado = grupos[position]
                    viewModel.obtenerContactosDeGrupo(grupoSeleccionado.id).observe(this@MainActivity) {
                        contactosAdapter.actualizarLista(it.contactos)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

    }
}