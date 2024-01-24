package com.example.proyecto_lekeitio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Act1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act1_enun)
    }

    fun volver(view: View) {
        val resultado = 99
        setResult(RESULT_OK, Intent().putExtra("RESULTADO", resultado))
        finish()
    }
}