package com.example.proyecto_lekeitio

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi

class Act1PuzzleActivity : AppCompatActivity() {

    // Declaración de variables
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
    private var bSeleccionado : Boolean = false
    private lateinit var iSeleccionado : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act1_puzzle)

        // Implementación de variables
        btnMapa = findViewById(R.id.btnMapa)

        // Ocultar botón siguiente
        btnMapa.visibility = View.INVISIBLE
        
    }

    fun siguiente(view: View) {
        finish()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun imagen1(view: View) {
        img1 = findViewById(R.id.img1)
        if (!bSeleccionado) {
            seleccionarImagen(img1)
        } else{
            cambiarImagen(iSeleccionado,img1)
        }
    }

    private fun seleccionarImagen(img: ImageView) {
        iSeleccionado = img;
        bSeleccionado = true
        img.isEnabled= false
    }


    private fun cambiarImagen(imagen1: ImageView, imagen2: ImageView) {
        var draSeleccionado = imagen1.drawable
        imagen1.setImageDrawable(imagen2.drawable)
        imagen2.setImageDrawable(draSeleccionado)
        imagen1.isEnabled = true
        bSeleccionado = false
    }


    fun imagen2(view: View) {
        img2 = findViewById(R.id.img2)
        if (!bSeleccionado) {
            seleccionarImagen(img2)
        } else{
            cambiarImagen(iSeleccionado,img2)
        }
    }

    fun imagen3(view: View) {
        img3 = findViewById(R.id.img3)
        if (!bSeleccionado) {
            seleccionarImagen(img3)
        } else{
            cambiarImagen(iSeleccionado,img3)
        }
    }

    fun imagen4(view: View) {
        img4 = findViewById(R.id.img4)
        if (!bSeleccionado) {
            seleccionarImagen(img4)
        } else{
            cambiarImagen(iSeleccionado,img4)
        }
    }

    fun imagen5(view: View) {
        img5 = findViewById(R.id.img5)
        if (!bSeleccionado) {
            seleccionarImagen(img5)
        } else{
            cambiarImagen(iSeleccionado,img5)
        }
    }

    fun imagen6(view: View) {
        img6 = findViewById(R.id.img6)
        if (!bSeleccionado) {
            seleccionarImagen(img6)
        } else{
            cambiarImagen(iSeleccionado,img6)
        }
    }

    fun imagen7(view: View) {
        img7 = findViewById(R.id.img7)
        if (!bSeleccionado) {
            seleccionarImagen(img7)
        } else{
            cambiarImagen(iSeleccionado,img7)
        }
    }

    fun imagen8(view: View) {
        img8 = findViewById(R.id.img8)
        if (!bSeleccionado) {
            seleccionarImagen(img8)
        } else{
            cambiarImagen(iSeleccionado,img8)
        }
    }

    fun imagen9(view: View) {
        img9 = findViewById(R.id.img9)
        if (!bSeleccionado) {
            seleccionarImagen(img9)
        } else{
            cambiarImagen(iSeleccionado,img9)
        }
    }
}