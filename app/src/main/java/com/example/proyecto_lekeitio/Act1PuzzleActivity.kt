package com.example.proyecto_lekeitio

import android.content.ClipData
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnDragListener
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

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
    private lateinit var esp1 : LinearLayout
    private lateinit var esp2 : LinearLayout
    private lateinit var esp3 : LinearLayout
    private lateinit var esp4 : LinearLayout
    private lateinit var esp5 : LinearLayout
    private lateinit var esp6 : LinearLayout
    private lateinit var esp7 : LinearLayout
    private lateinit var esp8 : LinearLayout
    private lateinit var esp9 : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act1_puzzle)

        // Implementación de variables
        btnMapa = findViewById(R.id.btnMapa)
        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        img4 = findViewById(R.id.img4)
        img5 = findViewById(R.id.img5)
        img6 = findViewById(R.id.img6)
        img7 = findViewById(R.id.img7)
        img8 = findViewById(R.id.img8)
        img9 = findViewById(R.id.img9)
        esp1 = findViewById(R.id.esp1)
        esp2 = findViewById(R.id.esp2)
        esp3 = findViewById(R.id.esp3)
        esp4 = findViewById(R.id.esp4)
        esp5 = findViewById(R.id.esp5)
        esp6 = findViewById(R.id.esp6)
        esp7 = findViewById(R.id.esp7)
        esp8 = findViewById(R.id.esp8)
        esp9 = findViewById(R.id.esp9)

        // Ocultar botón siguiente
        btnMapa.visibility = View.INVISIBLE
        
    }

    fun siguiente(view: View) {
        finish()
    }
}