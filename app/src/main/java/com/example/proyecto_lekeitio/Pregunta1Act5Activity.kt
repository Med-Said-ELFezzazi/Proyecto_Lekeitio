package com.example.proyecto_lekeitio

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

class Pregunta1Act5Activity : AppCompatActivity() {

    private lateinit var btnSiguiente:Button
    private var nCont: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta1_act5)

        btnSiguiente = findViewById<Button>(R.id.btnSiguienteAct5)

        btnSiguiente.isVisible = false
    }

    /*
     * Un metodo onClick para cada TextView
     */

    fun incorrecto1(view: View) {
        val lbResp1:TextView = findViewById(R.id.tvPre1Res1)
        incorrecto(lbResp1)
    }
    fun incorrecto2(view: View) {
        val lbResp2:TextView = findViewById(R.id.tvPre1Res2)
        incorrecto(lbResp2)
    }
    fun correcto1(view: View) {
        val lbResp3:TextView = findViewById(R.id.tvPre1Res3)
        correcto(lbResp3)
    }
    fun correcto2(view: View) {
        val lbResp4:TextView = findViewById(R.id.tvPre2Res1)
        correcto(lbResp4)
    }
    fun incorrecto3(view: View) {
        val lbResp5:TextView = findViewById(R.id.tvPre2Res2)
        incorrecto(lbResp5)
    }
    fun incorrecto4(view: View) {
        val lbResp6:TextView = findViewById(R.id.tvPre2Res3)
        incorrecto(lbResp6)
    }
    fun incorrecto5(view: View) {
        val lbResp7:TextView = findViewById(R.id.tvPre3Res1)
        incorrecto(lbResp7)
    }
    fun incorrecto6(view: View) {
        val lbResp8:TextView = findViewById(R.id.tvPre3Res2)
        incorrecto(lbResp8)
    }
    fun correcto3(view: View) {
        val lbResp9:TextView = findViewById(R.id.tvPre3Res3)
        correcto(lbResp9)
    }
    fun correcto4(view: View) {
        val lbResp10:TextView = findViewById(R.id.tvPre4Res1)
        correcto(lbResp10)
    }
    fun incorrecto7(view: View) {
        val lbResp11:TextView = findViewById(R.id.tvPre4Res2)
        incorrecto(lbResp11)
    }
    fun incorrecto8(view: View) {
        val lbResp12:TextView = findViewById(R.id.tvPre4Res3)
        incorrecto(lbResp12)
    }
    fun incorrecto9(view: View) {
        val lbResp13:TextView = findViewById(R.id.tvPre5Res1)
        incorrecto(lbResp13)
    }
    fun correcto5(view: View) {
        val lbResp14:TextView = findViewById(R.id.tvPre5Res2)
        correcto(lbResp14)
    }
    fun incorrecto10(view: View) {
        val lbResp15:TextView = findViewById(R.id.tvPre5Res3)
        incorrecto(lbResp15)
    }


    fun siguiente(view: View) {
        finish()
    }

    /**
     * Cambia el fondo del TextView pasado como parámetro a rojo y lo
     * desactiva.
     */
    private fun incorrecto(lbResp: TextView) {
        lbResp.setBackgroundColor(Color.RED)
        lbResp.isEnabled = false
    }

    /**
     * Cambia el fondo del TextView pasado como parámetro a rojo y lo
     * desactiva. Suma 1 en el contador y cuando llegue a 5, aparecerá
     * el botón siguiente.
     */
    private fun correcto (lbResp: TextView){
        lbResp.setBackgroundColor(Color.GREEN)
        lbResp.isEnabled = false
        nCont++
        if (nCont==5){
            btnSiguiente.isVisible = true
        }
    }
}