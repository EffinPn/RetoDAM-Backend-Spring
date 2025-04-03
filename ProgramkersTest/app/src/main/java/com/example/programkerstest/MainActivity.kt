package com.example.programkerstest

import android.content.Intent
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.programkerstest.model.Usuario
import com.example.programkerstest.retrofit.RetrofitCliente
import kotlinx.coroutines.launch
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {

    // Iniciamos las variables necesarias con las que vamos a trabajar
    // Usuarios - Registro
    private lateinit var usernameEditText: EditText
    private lateinit var nombreEditText: EditText
    private lateinit var apellidosEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registrarButton: Button

    // Usuarios - Login
    private lateinit var emailLoginEditText: EditText
    private lateinit var passwordLoginEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // REGISTRO DE USUARIOS
        // Recogemos los valores de edittext - usuarios
        usernameEditText = findViewById(R.id.usernameEditText)
        nombreEditText = findViewById(R.id.nombreEditText)
        apellidosEditText = findViewById(R.id.apellidosEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        registrarButton = findViewById(R.id.registrarButton)

        // Usar el botón de registro
        registrarButton.setOnClickListener{
            registrarUsuario()
        }

        // LOGIN DE USUARIO
        emailLoginEditText = findViewById(R.id.emailLoginEditText)
        passwordLoginEditText = findViewById(R.id.passwordLoginEditText)
        loginButton = findViewById(R.id.loginButton)

        // Boton de login
        loginButton.setOnClickListener {
            val email = emailLoginEditText.text.toString()
            val password = passwordLoginEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor introduzca correo y contraseña", Toast.LENGTH_SHORT).show()
            } else {
                loginUsuario(email, password)
            }
        }
    }


    // FUNCIONES DE ENDPOINTS
    private fun registrarUsuario() {
        // Obtener los datos del formulario - Usuarios
        val username = usernameEditText.text.toString()
        val nombre = nombreEditText.text.toString()
        val apellidos = apellidosEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        // Validación de campos para evitar que sea nulo
        if (username.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }

        // para formatear la fecha para fecha_registro
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = sdf.format(Date())

        // Crear el usuario con los datos del formulario
        val nuevoUsuario = Usuario(
            username = username,
            nombre = nombre,
            apellidos = apellidos,
            email = email,
            password = password,
            enabled = 1,
            fecha_registro = formattedDate
        )

        lifecycleScope.launch {
            try {
                // Llamada a la API
                val response = RetrofitCliente.apiService.registrarUsuario(nuevoUsuario)

                // Comprobamos si hay respuesta
                if (response.isSuccessful) {
                    // Si la respuesta funciona, mostramos la respuesta de la API
                    Toast.makeText(this@MainActivity, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                } else {
                    // Si la respuesta falla, mostramos mensaje de error, comprobamos username y email
                    val mensajeError = response.errorBody()?.string()
                    if (mensajeError != null) {
                        if(mensajeError.contains("username")){
                            Toast.makeText(this@MainActivity, "El username elegido ya existe",Toast.LENGTH_SHORT).show()
                        } else if(mensajeError.contains("email")){
                            Toast.makeText(this@MainActivity, "El email elegido ya existe", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this@MainActivity, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } catch (e: Exception) {
                // En caso de error, mostramos el mensaje de error de la excepción
                Toast.makeText(this@MainActivity, "Error en la conexión: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUsuario(email: String, password: String) {
        lifecycleScope.launch {
            try {
                // Llamada al API de login
                val response: Response<Usuario> = RetrofitCliente.apiService.loginUsuario(email, password)

                if (response.isSuccessful) { // Respuesta OK del servidor
                    val usuario = response.body()

                    // Si el login es correcto, llamamos a la actividad de cliente (ClienteActivity)
                    val intent = Intent(this@MainActivity, ClienteActivity::class.java)
                    intent.putExtra("usuario", usuario) // Pasamos el objeto usuario que se recoge en la Response
                    startActivity(intent)
                    finish() // Cierra la mainactivity

                } else {
                    // Respuesta fallida del servidor, almacenamos el String Response de Unauthorized y lo mostramos
                    val errorMessage = response.errorBody()?.string()
                    Toast.makeText(this@MainActivity, "Error en el login: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error de conexión: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
