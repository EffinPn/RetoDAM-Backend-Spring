package com.example.programkerstest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.programkerstest.R
import com.example.programkerstest.holders.VacantesHolder
import com.example.programkerstest.model.Usuario
import com.example.programkerstest.model.Vacante

class VacantesAdapter(private val vacantes: List<Vacante>, private val usuario: Usuario) : RecyclerView.Adapter<VacantesHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacantesHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VacantesHolder(layoutInflater.inflate(R.layout.item_vacantes, parent, false))
    }

    override fun getItemCount(): Int {
        return this.vacantes.size
    }

    override fun onBindViewHolder(holder: VacantesHolder, position: Int) {
        val item: Vacante = this.vacantes[position]
        holder.bind(item, usuario) // bindeamos las vacantes y al usuario logeado
    }
}