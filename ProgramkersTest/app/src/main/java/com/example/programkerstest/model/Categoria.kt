package com.example.programkerstest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

// Necesitamos Parcelize para pasarlos entre actividades (Objetos Vacantes para alta solicitudes)
@Parcelize
data class Categoria (
    @SerializedName("id_categoria") val id_categoria: Int,
    val nombre: String,
    val descripcion: String
) : Parcelable