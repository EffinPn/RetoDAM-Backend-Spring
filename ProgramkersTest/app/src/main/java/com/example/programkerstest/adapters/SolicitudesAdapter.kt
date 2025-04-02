package com.example.programkerstest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.programkerstest.R
import com.example.programkerstest.holders.SolicitudesHolder
import com.example.programkerstest.model.Solicitud

// Al igual que el holder necesitamos la variable para almacenar el id y usarla en el boton de cancelar solicitud
class SolicitudesAdapter(private val solicitudes: List<Solicitud>, private val deleteSolicitud: (Int) -> Unit) : RecyclerView.Adapter<SolicitudesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolicitudesHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SolicitudesHolder(layoutInflater.inflate(R.layout.item_solicitudes, parent, false), deleteSolicitud)
    }

    override fun getItemCount(): Int {
        return this.solicitudes.size
    }

    override fun onBindViewHolder(holder: SolicitudesHolder, position: Int) {
        val item: Solicitud = this.solicitudes[position]
        holder.bind(item)
    }
}