package com.example.proyecto_lekeitio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class EnunAct5Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enun_act5)
    }

    fun siguiente(view: View) {
        var intent = Intent(this, MapaActivity::class.java)
        startActivity(intent)
        finish()
    }
}