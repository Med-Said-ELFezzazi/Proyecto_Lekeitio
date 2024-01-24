package com.example.proyecto_lekeitio

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible

class Act1PuzzleActivity : AppCompatActivity() {

    // Declaración de variables
    private lateinit var dra : Array<Drawable>
    private lateinit var btnMapa : Button
    private lateinit var img1 : ImageView
    private lateinit var img2 : ImageView
    private lateinit var img3 : ImageView
    private lateinit var img4 : ImageView
    private lateinit var img5 : ImageView
    private lateinit var img6 : ImageView
    private lateinit var img7 : ImageView
    private lateinit var img8 : ImageView
    private lateinit var img9 : ImageView
    private lateinit var iSeleccionado : ImageView
    private lateinit var esp1 : LinearLayout
    private lateinit var esp2 : LinearLayout
    private lateinit var esp3 : LinearLayout
    private lateinit var esp4 : LinearLayout
    private lateinit var esp5 : LinearLayout
    private lateinit var esp6 : LinearLayout
    private lateinit var esp7 : LinearLayout
    private lateinit var esp8 : LinearLayout
    private lateinit var esp9 : LinearLayout
    private var bSeleccionado : Boolean = false
    private var nContador : Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act1_puzzle)

        // Instanciar los ImageView \\
        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        img4 = findViewById(R.id.img4)
        img5 = findViewById(R.id.img5)
        img6 = findViewById(R.id.img6)
        img7 = findViewById(R.id.img7)
        img8 = findViewById(R.id.img8)
        img9 = findViewById(R.id.img9)


        // Instanciar el array \\
        dra = arrayOf(img3.drawable, img8.drawable, img4.drawable, img2.drawable, img5.drawable,
            img9.drawable, img1.drawable, img7.drawable, img6.drawable)

        // Implementación de variables \\
        btnMapa = findViewById(R.id.btnMapa)

        // Ocultar botón siguiente
        btnMapa.visibility = View.INVISIBLE
    }

    /**
     * Cierra la ventana. Método onClick del botón.
     */
    fun siguiente(view: View) {
        finish()
    }

    /**
     * Guarda la imagen e indica al programa que ya hay un ImageView seleccionada. Desactiva el
     * ImageView para que no se seleccione de nuevo.
     */
    private fun seleccionarImagen(img: ImageView) {
        iSeleccionado = img;
        bSeleccionado = true
        img.isEnabled= false
    }

    /**
     * Intercambia los drawables de las ImageViews pasados como parámetros, indica al programa que
     * ya no hay un ImageView seleccionado y Activa el ImageView desactivado anteriormente.
     */
    private fun cambiarImagen(imagen1: ImageView, imagen2: ImageView) {
        val draSeleccionado = imagen1.drawable
        imagen1.setImageDrawable(imagen2.drawable)
        imagen2.setImageDrawable(draSeleccionado)
        imagen1.isEnabled = true
        bSeleccionado = false

    }

    /**
     * Modifica el ImageView pasado como parámetro para que no se pueda volver a seleccionar,
     * le da un estilo transparente para indicar que es correcto y añade +1 al contador. Cuando
     * este llegue a 9, se visualizará el botón para continuar, indicando que se superó el
     * ejercicio.
     */
    private fun  respuestaCorrecta(img:ImageView): ImageView {
        bSeleccionado = false
        img.isEnabled = false
        img.isClickable = false
        img.alpha = 0.7F
        nContador++
        if (nContador == 9){btnMapa.isVisible = true}
        return img
    }

    /**
     * Comprueba si cada ImageView contiene el drawable correcto. Si es así, ejecutará el método
     * respuestaCorrecta(ImageView).
     */
    private fun comprobarPosiciones(){
        if(img1.drawable.equals(dra[0])) {img1 = respuestaCorrecta(img1)}
        if(img2.drawable.equals(dra[1])) {img2 = respuestaCorrecta(img2)}
        if(img3.drawable.equals(dra[2])) {img3 = respuestaCorrecta(img3)}
        if(img4.drawable.equals(dra[3])) {img4 = respuestaCorrecta(img4)}
        if(img5.drawable.equals(dra[4])) {img5 = respuestaCorrecta(img5)}
        if(img6.drawable.equals(dra[5])) {img6 = respuestaCorrecta(img6)}
        if(img7.drawable.equals(dra[6])) {img7 = respuestaCorrecta(img7)}
        if(img8.drawable.equals(dra[7])) {img8 = respuestaCorrecta(img8)}
        if(img9.drawable.equals(dra[8])) {img9 = respuestaCorrecta(img9)}
    }

    // METODOS ONCLICK DE LOS TEXTVIEW \\
    fun imagen1(view: View) {
        if (!bSeleccionado) {
            seleccionarImagen(img1)
        } else{
            cambiarImagen(iSeleccionado,img1)
        }
        comprobarPosiciones()
    }

    fun imagen2(view: View) {
        if (!bSeleccionado) {
            seleccionarImagen(img2)
        } else{
            cambiarImagen(iSeleccionado,img2)
        }
        comprobarPosiciones()
    }

    fun imagen3(view: View) {
        if (!bSeleccionado) {
            seleccionarImagen(img3)
        } else{
            cambiarImagen(iSeleccionado,img3)
        }
        comprobarPosiciones()
    }

    fun imagen4(view: View) {
        if (!bSeleccionado) {
            seleccionarImagen(img4)
        } else{
            cambiarImagen(iSeleccionado,img4)
        }
        comprobarPosiciones()
    }

    fun imagen5(view: View) {
        if (!bSeleccionado) {
            seleccionarImagen(img5)
        } else{
            cambiarImagen(iSeleccionado,img5)
        }
        comprobarPosiciones()
    }

    fun imagen6(view: View) {
        if (!bSeleccionado) {
            seleccionarImagen(img6)
        } else{
            cambiarImagen(iSeleccionado,img6)
        }
        comprobarPosiciones()
    }

    fun imagen7(view: View) {
        if (!bSeleccionado) {
            seleccionarImagen(img7)
        } else{
            cambiarImagen(iSeleccionado,img7)
        }
        comprobarPosiciones()
    }

    fun imagen8(view: View) {
        if (!bSeleccionado) {
            seleccionarImagen(img8)
        } else{
            cambiarImagen(iSeleccionado,img8)
        }
        comprobarPosiciones()
    }

    fun imagen9(view: View) {
        if (!bSeleccionado) {
            seleccionarImagen(img9)
        } else{
            cambiarImagen(iSeleccionado,img9)
        }
        comprobarPosiciones()
    }
}