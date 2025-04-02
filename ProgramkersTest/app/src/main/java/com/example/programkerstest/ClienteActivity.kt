package com.example.programkerstest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.programkerstest.adapters.SolicitudesAdapter
import com.example.programkerstest.adapters.VacantesAdapter
import com.example.programkerstest.model.Solicitud
import com.example.programkerstest.model.Usuario
import com.example.programkerstest.model.Vacante
import com.example.programkerstest.retrofit.RetrofitCliente
import kotlinx.coroutines.launch
import retrofit2.Response

// Clase principal de cliente
class ClienteActivity : AppCompatActivity() {

    // Iniciamos las variables necesarias con las que vamos a trabajar
    // Usuario - necesaria para utilizar el username
    private lateinit var usuario: Usuario

    // Vacantes
    private lateinit var vacantesRecyclerView: RecyclerView
    private lateinit var vacantesAdapter: VacantesAdapter
    private lateinit var empresaEditText: EditText
    private lateinit var categoriaEditText: EditText
    private lateinit var descripcionEditText: EditText
    private lateinit var buscarVacantesButton: Button
    private val vacanteList = mutableListOf<Vacante>()

    // Solicitudes
    private lateinit var solicitudesRecyclerView: RecyclerView
    private lateinit var solicitudesAdapter: SolicitudesAdapter
    private lateinit var consultarSolicitudButton: Button
    private var solicitudesList = mutableListOf<Solicitud>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)

        // Recuperamos el usuario de la activity anterior
        usuario = intent.getParcelableExtra("usuario")!!

        // Verifica que el usuario no sea null
        if (usuario == null) {
            Log.e("ClienteActivity", "Usuario es null")
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
            return
        }

        // preparamos el segundo layout para el boton de cancelar, que estará en otro xml
        val layout2 = LayoutInflater.from(this).inflate(R.layout.item_solicitudes, null)


        // VACANTES
        // BUSCAR POR FILTROS EMPRESA CATEGORIA Y DESCRIPCION
        // llamamos al recyclerview
        vacantesRecyclerView = findViewById(R.id.vacantesRecyclerView)
        vacantesRecyclerView.layoutManager = LinearLayoutManager(this)

        // iniciamos el adapter, que contendra la lista de vacantes y al usuario logeado
        vacantesAdapter = VacantesAdapter(vacanteList, usuario)
        vacantesRecyclerView.adapter = vacantesAdapter

        // iniciamos variables (el boton se busca en el segundo layout)
        empresaEditText = findViewById(R.id.empresaEditText)
        categoriaEditText = findViewById(R.id.categoriaEditText)
        descripcionEditText = findViewById(R.id.descripcionEditText)
        buscarVacantesButton = findViewById(R.id.buscarVacantesButton)

        // Configurar el botón de búsqueda
        buscarVacantesButton.setOnClickListener {
            val empresa = empresaEditText.text.toString()
            val categoria = categoriaEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()

            buscarFiltrosVacantes(empresa, categoria, descripcion)
        }


        // SOLICITUDES
        // MOSTRAR SOLICITUDES
        // llamamos al recyclerview
        solicitudesRecyclerView = findViewById(R.id.solicitudesRecyclerView)
        solicitudesRecyclerView.layoutManager = LinearLayoutManager(this)

        // iniciamos adapter, teniendo en cuenta que necesitamos almacenar el id de cada solicitud
        solicitudesAdapter = SolicitudesAdapter(solicitudesList) {
                solicitudId -> cancelarSolicitud(solicitudId)
        }
        solicitudesRecyclerView.adapter = solicitudesAdapter

        // iniciamos variables
        consultarSolicitudButton = findViewById(R.id.consultarSolicitudButton)

        // CANCELAR SOLICITUDES -> BOTON UNA VEZ CONSULTADA LA LISTA DE SOLICITUDES
        // configuramos boton de consultar solicitudes
        consultarSolicitudButton.setOnClickListener {
            buscarSolicitudesUsuario(usuario.username)
        }
    }


    // FUNCIONES DE ENDPOINTS

    private fun buscarFiltrosVacantes(empresa: String?, categoria: String?, descripcion: String?) {
        lifecycleScope.launch {
            try {
                // Aquí pasamos los filtros a la API, que pueden ser nulos
                val response: Response<List<Vacante>> = RetrofitCliente.apiService.getVacantes(
                    empresa = empresa,
                    categoria = categoria,
                    descripcion = descripcion
                )
                if (response.isSuccessful) {
                    val vacantes = response.body() ?: emptyList()

                    // Las ordenamos por ID
                    val vacantesOrdenadas = vacantes.sortedBy { it.idVacante }

                    // Limpiamos la lista de vacantes y la llenamos con los resultados ordenados
                    vacanteList.clear()
                    vacanteList.addAll(vacantesOrdenadas)

                    // Notificamos al adaptador que los datos han cambiado
                    vacantesAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@ClienteActivity, "No se encontraron vacantes", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ClienteActivity, "Error al consultar vacantes: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buscarSolicitudesUsuario(userNameSolicitud : String){
        if (userNameSolicitud.isNullOrEmpty()) {
            Log.e("ClienteActivity", "Username es nulo o vacío")
            Toast.makeText(this@ClienteActivity, "No se puede consultar sin un username", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            try {
                // Llamamos a la API, pasandole el username
                val response = RetrofitCliente.apiService.getSolicitudesUsuario(username = userNameSolicitud)

                if (response.isSuccessful) {
                    val solicitudes = response.body() ?: emptyList()

                    // Limpiamos la lista de solicitudes y la llenamos con los resultados
                    solicitudesList.clear()
                    solicitudesList.addAll(solicitudes)

                    // Notificamos al adaptador que los datos han cambiado
                    solicitudesAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@ClienteActivity, "No se encontraron solicitudes", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error al consultar solicitudes", e)
                Toast.makeText(this@ClienteActivity, "Error al consultar las solicitudes", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cancelarSolicitud(solicitudId: Int) {
        lifecycleScope.launch {
            try {
                // Realizar la llamada a la API para cancelar la solicitud
                val response = RetrofitCliente.apiService.deleteSolicitud(solicitudId)

                if (response.isSuccessful) {
                    // Si la solicitud se elimina correctamente, eliminamos la solicitud de la lista local
                    solicitudesList.removeAll { it.idSolicitud == solicitudId }
                    solicitudesAdapter.notifyDataSetChanged()

                    Toast.makeText(this@ClienteActivity, "Solicitud cancelada con éxito", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ClienteActivity, "Error al cancelar la solicitud", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ClienteActivity, "Error al conectar: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}