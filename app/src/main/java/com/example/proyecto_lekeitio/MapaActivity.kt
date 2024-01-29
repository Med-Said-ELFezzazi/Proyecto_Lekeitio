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

    private var contador: Int = 1


    lateinit var btnsiguienteActividad: Button
   // val arrayActividades: Array<String> = arrayOf("actividad1", "actividad2" , "actividad3", "actividad4", "actividad5", "actividad6", "actividad7")
    lateinit var lblInstruccion : TextView


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
        when (contador) {
            1 -> intent = Intent(this, Act1EnunActivity::class.java)
            2 -> intent = Intent(this, Actividad2::class.java)
            3 -> intent = Intent(this, Act3Activity::class.java)
            4 -> intent = Intent(this, Act4Activity::class.java)
            5 -> intent = Intent(this, EnunAct5Activity::class.java)
            6 -> intent = Intent(this, Actividad6::class.java)
            7 -> intent = Intent(this, Actividad7::class.java)
            else -> {
                contador = 0
                intent = Intent(this, PantallaFinal::class.java)
            }
        }
        contador++
        startActivity(intent)
        btnsiguienteActividad.isVisible =false
        lblInstruccion.isVisible = true
    }
}