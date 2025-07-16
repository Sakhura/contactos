package com.sakhura.contactos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakhura.contactos.databinding.ItemContactoBinding
import com.sakhura.contactos.model.Contacto

class ContactosAdapter(
    private var lista: List<Contacto>,
    private val onItemClick: (Contacto) -> Unit
) : RecyclerView.Adapter<ContactosAdapter.ContactoViewHolder>() {

    inner class ContactoViewHolder(val binding: ItemContactoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contacto: Contacto) {
            binding.tvNombre.text = contacto.nombre
            binding.tvTelefono.text = contacto.telefono
            binding.root.setOnClickListener {
                onItemClick(contacto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        val binding = ItemContactoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactoViewHolder(binding)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    fun actualizarLista(nuevaLista: List<Contacto>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }
}

// Archivo: app/src/main/res/layout/item_contacto.xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:padding="16dp"
android:background="?android:attr/selectableItemBackground">

<TextView
android:id="@+id/tvNombre"
android:layout_width="0dp"
android:layout_height="wrap_content"
android:textSize="18sp"
android:textStyle="bold"
android:textColor="@android:color/black"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent"
tools:text="Juan Pérez" />

<TextView
android:id="@+id/tvTelefono"
android:layout_width="0dp"
android:layout_height="wrap_content"
android:layout_marginTop="4dp"
android:textSize="14sp"
android:textColor="@android:color/darker_gray"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toBottomOf="@+id/tvNombre"
tools:text="+56 9 1234 5678" />

</androidx.constraintlayout.widget.ConstraintLayout>   sourceCompatibility = JavaVersion.VERSION_11
targetCompatibility = JavaVersion.VERSION_11
}

kotlinOptions {
    jvmTarget = "11"
}
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // ViewModel & LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Fragment
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}