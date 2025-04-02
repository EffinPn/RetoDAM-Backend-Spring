package com.example.programkerstest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

// Necesitamos Parcelize para pasarlos entre actividades
@Parcelize
data class Usuario (
    @SerializedName("username") val username: String,
    val nombre: String,
    val apellidos: String,
    val email: String,
    val password: String,
    val enabled: Int,
    val fecha_registro: String
) : Parcelable