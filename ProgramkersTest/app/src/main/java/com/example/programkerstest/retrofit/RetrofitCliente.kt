package com.example.programkerstest.retrofit

import com.example.programkerstest.ApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


// objeto creado para no meter el retrofit directamente en el mainActivity (singleton)
object RetrofitCliente {

    // url base para todas las conexiones a la API
    private const val urlBase = "http://10.0.2.2:8080/"

    // creamos el conversor json de la API (necesitamos gson para formatear la fecha a yyyy-MM-dd)
    val gson = GsonBuilder().setLenient().create()


    // importante el orden de los converter, primero el Scalars (datos simples) y luego el de JSON
    private val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    // le comunicamos los endpoints de la API
    val apiService : ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}