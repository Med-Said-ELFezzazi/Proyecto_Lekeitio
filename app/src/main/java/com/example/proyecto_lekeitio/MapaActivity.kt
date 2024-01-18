package com.example.proyecto_lekeitio

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible

class MapaActivity : AppCompatActivity() {

    lateinit var btnsiguienteActividad: Button
    val arrayActividades: Array<String> = arrayOf("actividad1", "actividad2" , "actividad3", "actividad4", "actividad5", "actividad6", "actividad7")
    lateinit var lblInstruccion : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        var imgMapa : ImageView = findViewById(R.id.imgMapa)
        var lblTituloMapa : TextView = findViewById(R.id.lblTituloMapa)
        lblInstruccion = findViewById(R.id.lblInstruccion)
        btnsiguienteActividad = findViewById(R.id.btnsiguienteActividad)
        btnsiguienteActividad.isVisible = false
    }
    fun mostrarDatos(view: View) {
        lblInstruccion.isVisible = false
        btnsiguienteActividad.isVisible = true


    }

    /**
     * Metodo para pasar entre actividades
     */
    fun pasarActividades(view: View) {
        val intent = Intent(this, EnunAct5Activity::class.java)
        startActivityForResult(intent, 1234)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1234 && resultCode == RESULT_OK) {
            // Obtener el resultado de la actividad anterior
            val resultado = data?.getIntExtra("RESULTADO", 0)

            btnsiguienteActividad.isVisible = false
            lblInstruccion.isVisible = true
            lblInstruccion.setText(resultado.toString())
        }
    }

}