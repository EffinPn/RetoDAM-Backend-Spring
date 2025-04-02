package com.example.programkerstest.model

import com.google.gson.annotations.SerializedName

data class Solicitud(
    @SerializedName("id_solicitud") val idSolicitud: Int,
    val fecha: String,
    val archivo: String,
    val comentarios: String,
    val estado: Int,
    @SerializedName("vacante") val vacante: Vacante,
    @SerializedName("usuario") val username: Usuario
)