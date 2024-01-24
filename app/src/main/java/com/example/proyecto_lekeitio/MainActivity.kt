package com.example.proyecto_lekeitio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    //lateinit var btnInciar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnInciar : Button = findViewById(R.id.btnIniciar)

    }

    fun iniciar(view: View) {
        var intent = Intent(this, Act1PuzzleActivity::class.java)
        startActivity(intent)
    }
}