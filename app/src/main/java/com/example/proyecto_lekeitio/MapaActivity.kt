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


    @SuppressLint("MissingInflatedId")

    private var contador: Int = 3

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
        var intent : Intent = Intent(this,Act3Activity::class.java)
        if (contador == 4) {
            intent = Intent(this,Act4Activity::class.java)
        }
        contador++
        startActivity(intent)
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