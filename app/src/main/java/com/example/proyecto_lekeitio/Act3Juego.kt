package com.example.proyecto_lekeitio

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible

class Act3Juego : AppCompatActivity() {

    private var contador: Int = 0

    private lateinit var cuadradoVacio: EditText
    private lateinit var btnSiguiente: Button
    private lateinit var txtFotoSeleccionda: TextView

   // private lateinit var respuestaCorrecta: String

    private lateinit var txtNumPregunta: TextView

    //audios de respuestas
    private lateinit var mpCorrecto: MediaPlayer
    private lateinit var mpIncorrecto: MediaPlayer

    private lateinit var layImg1: LinearLayout
    private lateinit var layImg2: LinearLayout
    private lateinit var layImg3: LinearLayout
    private lateinit var layImg4: LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act3_juego)

        //layout
        layImg1 = findViewById(R.id.layoutImg1)
        layImg2 = findViewById(R.id.layoutImg2)
        layImg3 = findViewById(R.id.layoutImg3)
        layImg4 = findViewById(R.id.layoutImg4)


        txtFotoSeleccionda = findViewById(R.id.txtFotoseleccionada)
        btnSiguiente = findViewById(R.id.btnSiguiente)
        btnSiguiente.isEnabled = false

        cuadradoVacio = findViewById(R.id.cuadradoVacio)

        txtNumPregunta = findViewById(R.id.txtNumPregunta)      //pa cambiar el número de pregunta

        //¿Cuál es el instrumento utilizado en el video?
        val textoConFormato = """
        <html><body>
        <h1 style="text-align:center; font-weight:bold;">Zein da bideoan erabiltzen den tresna?</h1>
        <p style="text-align:center; color: black;">'Putz egiten diogun zerbait'</p>
        </body></html>
         """.trimIndent()

        //silbido
        cuadradoVacio.text = Html.fromHtml(textoConFormato, Html.FROM_HTML_MODE_LEGACY) as Editable?
        cuadradoVacio.isFocusable = false
        cuadradoVacio.isClickable = false
        cuadradoVacio.isCursorVisible = false

        //respuestaCorrecta = "silbido"   //Inicialización (respuesta de pregunta1)

        // Inicializa los objetos MediaPlayer
        mpCorrecto = MediaPlayer.create(this, R.raw.yeah)
        mpIncorrecto = MediaPlayer.create(this, R.raw.wrong_answer)

        btnSiguiente.setOnClickListener {
            if (contador == 2) {
                finish()
            }
          /*  if (respuestaCorrecta == "silbido") {
                respuestaCorrecta = "pandereta"
                // Restablecer las banderas para la siguiente pregunta
                imagen4Seleccionada = false
                imagen2Seleccionada = false
            }*/
            //Poner número de pregunto 2
            txtNumPregunta.text = "2. Galdera"
            val textoConFormatoPregunta2 = """
            <html><body>
            <h1 style="text-align:center; font-weight:bold;">Zein da bideoan erabiltzen den tresna?</h1>
            <p style="text-align:center; color: black;">'Eskuarekin ukitzen dugun zerbait'</p>
            </body></html>
            """.trimIndent()

            cuadradoVacio.text = Html.fromHtml(textoConFormatoPregunta2, Html.FROM_HTML_MODE_LEGACY) as Editable?
            cuadradoVacio.isFocusable = false
            cuadradoVacio.isClickable = false
            cuadradoVacio.isCursorVisible = false

            btnSiguiente.isEnabled =false

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
        // Lista de LinearLayouts que necesitan ser reseteados
        val layouts = listOf(layImg1, layImg2, layImg3, layImg4)

        // Recorrer cada LinearLayout y quitar cualquier borde
        layouts.forEach { layout ->
            layout.setBackgroundResource(0)
        }
    }



    fun imagenSeleccionada(view: View) {
        resetBorders() // Quitar el borde colorado de la elección anterior

        val imageView = view as ImageView
        var layoutSeleccionado: LinearLayout? = null

        when (view.id) {
            R.id.imagen1 -> layoutSeleccionado = layImg1
            R.id.imagen2 -> layoutSeleccionado = layImg2
            R.id.imagen3 -> layoutSeleccionado = layImg3
            R.id.imagen4 -> layoutSeleccionado = layImg4
        }

        if (layoutSeleccionado != null) {
            when (imageView.id) {
                // Casos incorrectos
                R.id.imagen1, R.id.imagen3 -> {
                    manejarSeleccionIncorrecta(layoutSeleccionado)
                }

                // Caso correcto de la pregunta
                R.id.imagen4, R.id.imagen2 -> {
                    manejarSeleccionCorrecta(imageView.id, layoutSeleccionado)
                }
            }
        }
    }

    private fun manejarSeleccionCorrecta(imageViewId: Int, layoutSeleccionado: LinearLayout) {
        val esSeleccionCorrecta = (contador == 0 && imageViewId == R.id.imagen4) || (contador == 1 && imageViewId == R.id.imagen2)

        if (esSeleccionCorrecta) {
            txtFotoSeleccionda.setText("Oso ondo zuzena") //Muy bien correcto
            txtFotoSeleccionda.setTextColor(resources.getColor(R.color.verde))
            lanzarAudioCorrecto()
            layoutSeleccionado.setBackgroundResource(R.drawable.borde_verde)
            btnSiguiente.isEnabled = true
            contador++
        } else {
            manejarSeleccionIncorrecta(layoutSeleccionado)
        }
    }

    private fun manejarSeleccionIncorrecta(layoutSeleccionado: LinearLayout) {
        txtFotoSeleccionda.setText("Hautaketa okerra!") //Seleccion incorrecta!
        txtFotoSeleccionda.setTextColor(resources.getColor(R.color.rojo))
        lanzarAudioIncorrecto()
        layoutSeleccionado.setBackgroundResource(R.drawable.borde_rojo)
    }

    /**
     * Metodo que vuelve al mapa
     */
    fun volverMapa(view: View) {
        finish()
    }
}
