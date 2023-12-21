package com.example.proyecto_lekeitio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Act1EnunActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act1_enun)
    }

    fun siguiente(view: View) {
        val intent = Intent(this, Act1PuzzleActivity::class.java)
        startActivityForResult(intent, 1234)
        finish()
    }

    fun volver(view: View) {
        finish()
    }

}