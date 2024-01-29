package com.example.proyecto_lekeitio

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.core.view.isVisible

class MapaActivity : AppCompatActivity() {

    private var contador: Int = 1

    lateinit var btnsiguienteActividad: Button
    // val arrayActividades: Array<String> = arrayOf("actividad1", "actividad2" , "actividad3", "actividad4", "actividad5", "actividad6", "actividad7")
    lateinit var lblInstruccion : TextView

    lateinit var videoMapa1 : VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        lblInstruccion = findViewById(R.id.lblInstruccion)
        btnsiguienteActividad = findViewById(R.id.btnsiguienteActividad)
        btnsiguienteActividad.isVisible = false

        /*  videoMapa1 = findViewById(R.id.videoMapa1)
          videoMapa1.setVideoPath("android.resource://" + packageName + "/" + R.raw.mapa1)
          videoMapa1.requestFocus()
          videoMapa1.start() */

        // Obtener el ID del video del intent, si no hay ninguno, usar mapa1 como predeterminado
        val videoResId = intent.getIntExtra("VIDEO_ID", R.raw.mapa1)
        contador = intent.getIntExtra("CONTADOR", 1)  // Usa 1 como valor predeterminado si no se encuentra "CONTADOR"

        videoMapa1 = findViewById(R.id.videoMapa1)
        videoMapa1.setVideoPath("android.resource://" + packageName + "/" + videoResId)
        videoMapa1.requestFocus()
        videoMapa1.start()


        // Hacer que el video se repita automáticamente al terminar
        videoMapa1.setOnCompletionListener {
            videoMapa1.start()
        }

        // Manejar clics en el video (opcional, dependiendo de tu flujo de usuario)
        videoMapa1.setOnClickListener {
            lblInstruccion.isVisible = false
            btnsiguienteActividad.isVisible = true
        }


        // Manejar el clic en el botón siguiente para detener el video
        btnsiguienteActividad.setOnClickListener {
            videoMapa1.stopPlayback() // Detiene la reproducción del video
            pasarActividades(it) // Continúa con la siguiente actividad
        }
    }


    fun mostrarDatos(view: View) {
        lblInstruccion.isVisible = false
        btnsiguienteActividad.isVisible = true
    }

    /**
     * Metodo para pasar entre actividades
     */
    /*fun pasarActividades(view: View) {
        when (contador) {
            1 -> intent = Intent(this, Act1EnunActivity::class.java)
            2 -> intent = Intent(this, Actividad2::class.java)
            3 -> intent = Intent(this, Act3Activity::class.java)
            4 -> intent = Intent(this, Act4Activity::class.java)
            5 -> intent = Intent(this, EnunAct5Activity::class.java)
            6 -> intent = Intent(this, Actividad6::class.java)
            7 -> intent = Intent(this, Actividad7::class.java)
            else -> {
                contador = 1
                intent = Intent(this, Act1EnunActivity::class.java)
            }
        }
        contador++
        startActivity(intent)
    }*/

    fun pasarActividades(view: View) {
        val siguienteActivityClass = when (contador) {
            1 -> Act1EnunActivity::class.java
            2 -> Actividad2::class.java
            3 -> Act3Activity::class.java
            4 -> Act4Activity::class.java
            5 -> EnunAct5Activity::class.java
            6 -> Actividad6::class.java
            7 -> Actividad7::class.java
            else -> {
                contador = 1
                Act1EnunActivity::class.java
            }
        }
        contador++
        val intent = Intent(this, siguienteActivityClass)
        intent.putExtra("CONTADOR", contador + 1) // Pasar el contador incrementado para la próxima actividad
        startActivity(intent)
        finish()  // Finaliza MapaActivity para que no vuelva a ella al presionar el botón de volver
    }

}