package com.example.proyecto_lekeitio

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible

class Act3Juego : AppCompatActivity() {

    private lateinit var cuadradoVacio: EditText
    private lateinit var btnSiguiente: Button
    private lateinit var txtFotoSeleccionda: TextView

    private lateinit var respuestaCorrecta: String

    private lateinit var txtNumPregunta: TextView

    //audios de respuestas
    private lateinit var mpCorrecto: MediaPlayer
    private lateinit var mpIncorrecto: MediaPlayer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act3_juego)

        txtFotoSeleccionda = findViewById(R.id.txtFotoseleccionada)
        btnSiguiente = findViewById(R.id.btnSiguiente)
        btnSiguiente.isVisible = false

        cuadradoVacio = findViewById(R.id.cuadradoVacio)

        txtNumPregunta = findViewById(R.id.txtNumPregunta)      //pa cambiar el número de pregunta

        // Establecer el texto predeterminado en el EditText
        val textoConFormato = """
        <html><body>
        <h1 style="text-align:center; font-weight:bold;">¿Cuál es el instrumento utilizado en el video?</h1>
        <p style="text-align:center; color: blue;">(Selección: silbido)</p>
        </body></html>
         """.trimIndent()



        cuadradoVacio.text = Html.fromHtml(textoConFormato, Html.FROM_HTML_MODE_LEGACY) as Editable?
        cuadradoVacio.isFocusable = false
        cuadradoVacio.isClickable = false
        cuadradoVacio.isCursorVisible = false

        respuestaCorrecta = "silbido"   //Inicialización (respuesta de pregunta1)

        // Inicializa los objetos MediaPlayer
        mpCorrecto = MediaPlayer.create(this, R.raw.yeah)
        mpIncorrecto = MediaPlayer.create(this, R.raw.wrong_answer)

        btnSiguiente.setOnClickListener {
//            respuestaCorrecta = "pandereta"
            if (respuestaCorrecta == "silbido") {
                respuestaCorrecta = "pandereta"
            }
            //Poner número de pregunto 2
            txtNumPregunta.text = "Pregunta número 2"
            val textoConFormatoPregunta2 = """
            <html><body>
            <h1 style="text-align:center; font-weight:bold;">¿Cuál es el instrumento utilizado en el video?</h1>
            <p style="text-align:center; color: blue;">(Seleccióna: pandereta)</p>
            </body></html>
            """.trimIndent()

            cuadradoVacio.text = Html.fromHtml(textoConFormatoPregunta2, Html.FROM_HTML_MODE_LEGACY) as Editable?
            cuadradoVacio.isFocusable = false
            cuadradoVacio.isClickable = false
            cuadradoVacio.isCursorVisible = false

            btnSiguiente.isVisible=false

            txtFotoSeleccionda.setText("")          //quitar el texto indicativo al pasar a la segunda pregunta

            resetBorders()                          //quitar el borde colorado al pasar al segunda pregunta

            btnSiguiente.setText("Finalizar")      //Cambiar el nombre del bóton a finalizar
        }
    }

    /**
     * Metodo que lanza el audio correcto
     */
    private fun lanzarAudioCorrecto() {
        if (mpCorrecto.isPlaying) {
            mpCorrecto.stop()
            mpCorrecto.release()
            mpCorrecto = MediaPlayer.create(this, R.raw.yeah)
        }
        mpCorrecto.start()
    }

    /**
     * Metodo que lanza el audio Incorrecto
     */
    private fun lanzarAudioIncorrecto() {
        if (mpIncorrecto.isPlaying) {
            mpIncorrecto.stop()
            mpIncorrecto.release()
            mpIncorrecto = MediaPlayer.create(this, R.raw.wrong_answer)
        }
        mpIncorrecto.start()
    }

    /**
     * Metodo que quita el borde de respuesta (rojo/verde)
     */
    private fun resetBorders() {
        val imageViews = listOf(R.id.imagen1, R.id.imagen2, R.id.imagen3, R.id.imagen4)
        imageViews.forEach { id ->
            findViewById<ImageView>(id).setBackgroundResource(0) // Quita cualquier borde
        }
    }

    fun imagenSeleccionada(view: View) {
        resetBorders()      //quitar el borde colorado de la eleccion anterior un vez pulsado la seguiente foto
        val imageView = view as ImageView
        when (view.id) {
            R.id.imagen1, R.id.imagen3 -> {
                txtFotoSeleccionda.setText("Seleccion incorrecta!")
                txtFotoSeleccionda.setTextColor(resources.getColor(R.color.rojo)) //text
                imageView.setBackgroundResource(R.drawable.borde_rojo)            //fondo
                lanzarAudioIncorrecto()                                           //audio
            }

            //Caso correcto de la pregunta 1
            R.id.imagen4 -> {
                if (respuestaCorrecta == "silbido") {
                    txtFotoSeleccionda.setText("Muy bien correcto")
                    txtFotoSeleccionda.setTextColor(resources.getColor(R.color.verde))  //texto en verde
                    imageView.setBackgroundResource(R.drawable.borde_verde)   //borde verde
                    lanzarAudioCorrecto()                 //audio
                    btnSiguiente.isVisible = true         //poner visible el bóton siguiente
                } else {
                    txtFotoSeleccionda.setText("Seleccion incorrecta!")
                    txtFotoSeleccionda.setTextColor(resources.getColor(R.color.rojo))
                    imageView.setBackgroundResource(R.drawable.borde_rojo)
                    lanzarAudioIncorrecto()                 //audio
                }
            }

            //Caso correcto de la pregunta 2
            R.id.imagen2 -> {
                if (respuestaCorrecta == "pandereta") {
                    txtFotoSeleccionda.setText("Muy bien correcto")
                    txtFotoSeleccionda.setTextColor(resources.getColor(R.color.verde))
                    imageView.setBackgroundResource(R.drawable.borde_verde)
                    btnSiguiente.isVisible = true
                    lanzarAudioCorrecto()
                } else {
                    txtFotoSeleccionda.setText("Seleccion incorrecta!")
                    txtFotoSeleccionda.setTextColor(resources.getColor(R.color.rojo))
                    imageView.setBackgroundResource(R.drawable.borde_rojo)
                    lanzarAudioIncorrecto()
                }
            }
        }
    }
}
