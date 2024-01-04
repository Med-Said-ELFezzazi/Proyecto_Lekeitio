package com.example.proyecto_lekeitio

import android.annotation.SuppressLint
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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act3_juego)

        txtFotoSeleccionda = findViewById(R.id.txtFotoseleccionada)
        btnSiguiente = findViewById(R.id.btnSiguiente)
        btnSiguiente.isVisible = false

        cuadradoVacio = findViewById(R.id.cuadradoVacio)

        // Establecer el texto predeterminado en el EditText
        val textoConFormato = """
        <html><body>
        <h1 style="text-align:center; font-weight:bold;">Pregunta número 1</h1>
        <p>¿Cuál es el instrumento utilizado?</p>
        <p>(Elección correcta: silbido)</p>
        </body></html>
         """.trimIndent()



        cuadradoVacio.text = Html.fromHtml(textoConFormato, Html.FROM_HTML_MODE_LEGACY) as Editable?
        cuadradoVacio.isFocusable = false
        cuadradoVacio.isClickable = false
        cuadradoVacio.isCursorVisible = false

        respuestaCorrecta = "silbido"
        btnSiguiente.setOnClickListener {
//            respuestaCorrecta = "pandereta"
            if (respuestaCorrecta == "silbido") {
                respuestaCorrecta = "pandereta"
            }
            val textoConFormatoPregunta2 = """
            <html><body>
            <h1 style="text-align:center; font-weight:bold;">Pregunta número 2</h1>
            <p style="margin-left: 20px;">¿Cuál es el instrumento utilizado?</p>
            <p style="margin-left: 20px;">(Elección correcta: pandereta)</p>
            </body></html>
            """.trimIndent()

            cuadradoVacio.text = Html.fromHtml(textoConFormatoPregunta2, Html.FROM_HTML_MODE_LEGACY) as Editable?
            cuadradoVacio.isFocusable = false
            cuadradoVacio.isClickable = false
            cuadradoVacio.isCursorVisible = false

            btnSiguiente.isVisible=false
        }
    }

    fun imagenSeleccionada(view: View) {
        val imageView = view as ImageView
        when (view.id) {
            R.id.imagen1, R.id.imagen3 -> {
                txtFotoSeleccionda.setText("Seleccion incorrecta!")
                txtFotoSeleccionda.setTextColor(resources.getColor(R.color.rojo))
                imageView.setBackgroundResource(R.drawable.borde_rojo)
            }

            R.id.imagen2 -> {
                if (respuestaCorrecta == "pandereta") {
                    txtFotoSeleccionda.setText("Muy bien correcto")
                    txtFotoSeleccionda.setTextColor(resources.getColor(R.color.verde))
                    imageView.setBackgroundResource(R.drawable.borde_verde)
                    btnSiguiente.isVisible = true
                } else {
                    txtFotoSeleccionda.setText("Seleccion incorrecta!")
                    txtFotoSeleccionda.setTextColor(resources.getColor(R.color.rojo))
                    imageView.setBackgroundResource(R.drawable.borde_rojo)
                }
            }

            R.id.imagen4 -> {
                if (respuestaCorrecta == "silbido") {
                    txtFotoSeleccionda.setText("Muy bien correcto")
                    txtFotoSeleccionda.setTextColor(resources.getColor(R.color.verde))
                    imageView.setBackgroundResource(R.drawable.borde_verde)
                    btnSiguiente.isVisible = true
                } else {
                    txtFotoSeleccionda.setText("Seleccion incorrecta!")
                    txtFotoSeleccionda.setTextColor(resources.getColor(R.color.rojo))
                    imageView.setBackgroundResource(R.drawable.borde_rojo)
                }
            }
        }
    }

}
