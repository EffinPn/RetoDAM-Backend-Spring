package com.example.programkerstest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

// Necesitamos Parcelize para pasarlos entre actividades (Objeto Vacante para alta solicitudes)
@Parcelize
data class Empresa (
    @SerializedName("id_empresa") val id_empresa: Int,
    val razon_social: String,
    val direccion_fiscal: String,
    val pais: String
) : Parcelable