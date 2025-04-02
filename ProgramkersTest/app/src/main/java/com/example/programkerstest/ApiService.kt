package com.example.programkerstest

import com.example.programkerstest.model.Solicitud
import com.example.programkerstest.model.Usuario
import com.example.programkerstest.model.Vacante
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // Registro de usuarios
    @POST("usuarios/registro")
    suspend fun registrarUsuario(@Body usuario: Usuario): Response<String>

    // Login de usuario
    @GET("usuarios/login")
    suspend fun loginUsuario(@Query("email") email: String,
                             @Query("password") password: String) : Response<Usuario>

    // Busqueda vacantes
    @GET("vacantes/buscarFiltros")
    suspend fun getVacantes(@Query("empresa") empresa: String? = null,
                    @Query("categoria") categoria: String? = null,
                    @Query("descripcion") descripcion: String? = null) : Response<List<Vacante>>

    // Solicitud de vacante
    @POST("solicitudes/solicitar")
    @Headers("Content-Type: application/json")
    suspend fun addSolicitud(@Body solicitud: Solicitud): Response<Solicitud>

    // Consultar solicitudes del usuario
    @GET("solicitudes/getByUsuario")
    suspend fun getSolicitudesUsuario(@Query("username") username: String): Response<List<Solicitud>>

    // Cancelar una solicitud
    @DELETE("solicitudes/cancelarSolicitud")
    suspend fun deleteSolicitud(@Query("id") idSolicitud: Int) : Response<String>

}