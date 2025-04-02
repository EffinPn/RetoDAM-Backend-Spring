package com.example.programkerstest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.programkerstest.model.Solicitud
import com.example.programkerstest.model.Usuario
import com.example.programkerstest.model.Vacante
import com.example.programkerstest.retrofit.RetrofitCliente
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Clase para dar de alta las solicitudes
class SolicitudActivity: AppCompatActivity() {
    // Preparamos las iniciales que vamos a utilizar, entre ellos los que almacenan la vacante y al usuario
    private lateinit var vacante: Vacante
    private lateinit var usuario: Usuario
    private lateinit var comentariosEditText: EditText
    private lateinit var solicitarVacanteButton: Button
    private lateinit var vacanteNombreTextView: TextView
    private lateinit var vacanteDescripcionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitud)

        // Nos traemos la vacante seleccionada y el usuario logeado
        vacante = intent.getParcelableExtra("vacante")!!
        usuario = intent.getParcelableExtra("usuario")!!
        // Agregar logs para depuración
        Log.d("SolicitudActivity", "Recibido vacante: ${vacante?.nombre}")
        Log.d("SolicitudActivity", "Recibido usuario: ${usuario?.username}")

        // Relacionamos con los campos del xml
        vacanteNombreTextView = findViewById(R.id.vacanteNombre)
        vacanteDescripcionTextView = findViewById(R.id.vacanteDescripcion)
        comentariosEditText = findViewById(R.id.comentariosEditText)
        solicitarVacanteButton = findViewById(R.id.solicitarVacanteButton)

        // Sacamos la información de la vacante
        vacanteNombreTextView.text = vacante.nombre
        vacanteDescripcionTextView.text = vacante.descripcion

        // Con el botón llamamos a la función de solicitar
        solicitarVacanteButton.setOnClickListener {
            realizarSolicitud()
        }
    }


    // FUNCION DE ENDPOINT
    private fun realizarSolicitud() {
        // Obtenemos los datos del formulario de solicitud
        val comentario = comentariosEditText.text.toString()

        // Preparamos la fecha de solicitud, al igual que en el registro de usuario hay que formatearla para BBDD MySql
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = sdf.format(Date())

        // Creamos el objeto Solicitud a insertar (el id y el estado, que son autoincrement y default 1)
        val solicitud = Solicitud(
            idSolicitud = 0,
            fecha = formattedDate,
            archivo = "",
            comentarios = comentario,
            estado = 1,
            vacante = vacante,
            username = usuario
        )

        lifecycleScope.launch {
            try {
                val response = RetrofitCliente.apiService.addSolicitud(solicitud)

                if (response.isSuccessful) {
                    // Si la solicitud fue exitosa, mostramos un mensaje de éxito
                    Toast.makeText(this@SolicitudActivity, "Solicitud enviada correctamente", Toast.LENGTH_SHORT).show()
                    finish()  // Finalizamos la actividad actual (o puedes redirigir a otra actividad)
                } else {
                    // Si la solicitud falla, mostramos un mensaje de error detallado
                    val errorMessage = response.errorBody()?.string()
                    Log.e("SolicitudActivity", "Error al enviar solicitud: $errorMessage")
                    Toast.makeText(this@SolicitudActivity, "Error al enviar la solicitud", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Capturamos cualquier excepción y mostramos un mensaje de error
                Toast.makeText(this@SolicitudActivity, "Error al procesar la solicitud: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

