package com.example.programkerstest.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.programkerstest.databinding.ItemSolicitudesBinding
import com.example.programkerstest.model.Solicitud

// necesario añadir la variable en el holder para el boton de cancelar y almacenar el ID correspondiente
class SolicitudesHolder (view: View, private val deleteSolicitud: (Int) -> Unit): RecyclerView.ViewHolder(view){
    private val binding = ItemSolicitudesBinding.bind(view)

    fun bind(solicitudes : Solicitud){
        // textos de recyclerview de solicitudes
        binding.solicitudIdVacante.text = "Nº de vacante: ${solicitudes.idVacante}"
        binding.solicitudNombreVacante.text = solicitudes.nombreVacante // así viajamos desde solicitud a vacante, y cogemos el nombre
        binding.solicitudEstado.text = "Estado: ${solicitudes.estado}"
        binding.solicitudComentarios.text = solicitudes.comentarios
        binding.solicitudFecha.text = "Fecha: ${solicitudes.fecha}"

        // necesitamos configurar el boton para que capture el id correspondiente a dicha solicitud (para el delete)
        binding.solicitudCancelarButton.setOnClickListener {
            deleteSolicitud(solicitudes.idSolicitud)
        }
    }
}