package com.example.proyecto_lekeitio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager

class Act1Activity : AppCompatActivity() {
    private val bCompletado : Boolean = false
    private val fragmentManager : FragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act1)

        val act1Fragment : FragmentContainerView = findViewById(R.id.frActividad1)

        fragmentManager.beginTransaction().replace(R.id.frActividad1, EnunciadoAct1Fragment::class.java,null).
        setReorderingAllowed(true).
        addToBackStack("Enunciado").
        commit()
    }


    fun volver(view: View) {
        val resultado = 1
        setResult(RESULT_OK, Intent().putExtra("RESULTADO", resultado))
        finish()
    }

    fun siguiente(view: View) {
        if (!bCompletado){
            fragmentManager.beginTransaction().replace(R.id.frActividad1, PuzzleAct1Fragment::class.java,null).
            setReorderingAllowed(true).
            addToBackStack("Enunciado").
            commit()
        }else{
            finish()
        }
    }
}