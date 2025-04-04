package com.example.programkerstest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

// Necesitamos Parcelize para pasarlos entre actividades
@Parcelize
data class Vacante(
    @SerializedName("id_vacante") val idVacante: Int,
    val nombre: String,
    val descripcion: String,
    val fecha: String,
    val salario: Double,
    val estatus: String,
    val destacado: Boolean,
    val imagen: String,
    val detalles: String,
    @SerializedName("empresa") val empresa: Empresa,
    @SerializedName("categoria") val categoria: Categoria
) : Parcelable