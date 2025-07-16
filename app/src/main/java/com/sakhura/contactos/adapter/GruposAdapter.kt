package com.sakhura.contactos.adapter

class GruposAdapter(
    private var grupos: List<Grupo>,
    private val onClick: (Grupo) -> Unit
) : RecyclerView.Adapter<GruposAdapter.GrupoViewHolder>() {

    inner class GrupoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreGrupo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrupoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grupo, parent, false)
        return GrupoViewHolder(view)
    }

    override fun onBindViewHolder(holder: GrupoViewHolder, position: Int) {
        val grupo = grupos[position]
        holder.tvNombre.text = grupo.nombre
        holder.itemView.setOnClickListener { onClick(grupo) }
    }

    override fun getItemCount(): Int = grupos.size

    fun actualizarLista(nuevaLista: List<Grupo>) {
        grupos = nuevaLista
        notifyDataSetChanged()
    }
}
