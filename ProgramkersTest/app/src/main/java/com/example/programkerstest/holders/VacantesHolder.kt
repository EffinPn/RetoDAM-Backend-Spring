package com.example.programkerstest.holders

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.programkerstest.SolicitudActivity
import com.example.programkerstest.databinding.ItemVacantesBinding
import com.example.programkerstest.model.Usuario
import com.example.programkerstest.model.Vacante
import com.squareup.picasso.Picasso

class VacantesHolder (view: View) : RecyclerView.ViewHolder(view){
    private val binding = ItemVacantesBinding.bind(view)

    // necesitamos el usuario para el boton de solicitar vacante manteniendo el username
    fun bind(vacantes : Vacante, usuario: Usuario){
        // Preparacion para las imagenes
        Picasso.get().load(vacantes.imagen).into(binding.imagenVacante)

        // Textos de los textview de vacantes
        binding.numeroVacante.text = "Nº Vacante: ${vacantes.idVacante}"
        binding.nombreVacante.text = vacantes.nombre
        binding.empresaVacante.text = vacantes.empresa.razon_social
        binding.descripcionVacante.text = vacantes.descripcion
        binding.salarioVacante.text = "Salario:${vacantes.salario}"

        // Necesitamos configurar el boton de solicitar una vacante, manteniendo informacion de Vacante y Usuario
        binding.solicitarButton.setOnClickListener {
            val activityActual = itemView.context
            val intent = Intent(activityActual, SolicitudActivity::class.java)
            intent.putExtra("vacante", vacantes)
            intent.putExtra("usuario", usuario)

            activityActual.startActivity(intent)
        }
    }
}