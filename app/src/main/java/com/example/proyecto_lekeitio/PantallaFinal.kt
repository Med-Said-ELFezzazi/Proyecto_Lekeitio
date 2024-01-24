package com.example.proyecto_lekeitio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PantallaFinal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gif_felicitaciones_final)

        var btnVolverMapa : Button = findViewById(R.id.btnVolverMapa)

        btnVolverMapa.setOnClickListener{
            finish()
        }
    }
}