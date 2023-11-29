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
    val array: Array<String> = arrayOf("actividad1", "actividad2" , "actividad3", "actividad4", "actividad5", "actividad6", "actividad7")
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
        var intent = Intent(this, Act1Activity::class.java)
        startActivity(intent)

    }

}