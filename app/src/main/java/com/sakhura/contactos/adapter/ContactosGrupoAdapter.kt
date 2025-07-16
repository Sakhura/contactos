package com.sakhura.contactos.adapter

class ContactosGrupoAdapter(
    private val contactos: List<Contacto>,
    private val onChecked: (Contacto, Boolean) -> Unit
) : RecyclerView.Adapter<ContactosGrupoAdapter.ContactoViewHolder>() {

    private val seleccionados = mutableSetOf<Int>()

    inner class ContactoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.checkboxContacto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_checkbox_contacto, parent, false)
        return ContactoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        val contacto = contactos[position]
        holder.checkBox.text = contacto.nombre
        holder.checkBox.isChecked = seleccionados.contains(contacto.id)

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                seleccionados.add(contacto.id)
            } else {
                seleccionados.remove(contacto.id)
            }
            onChecked(contacto, isChecked)
        }
    }

    override fun getItemCount() = contactos.size
}
