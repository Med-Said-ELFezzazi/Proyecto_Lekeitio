package com.example.proyecto_lekeitio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Act1PuzzleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act1_puzzle)


    }

    fun siguiente(view: View) {
        finish()
    }
}