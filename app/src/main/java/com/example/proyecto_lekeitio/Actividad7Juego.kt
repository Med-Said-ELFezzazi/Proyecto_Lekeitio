package com.example.proyecto_lekeitio


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Actividad7Juego : AppCompatActivity() {
    private var currentSelection: Pair<TextView?, TextView?> = Pair(null, null)
    private var correctPairs: MutableList<Pair<Int, Int>> = mutableListOf() // Pares de IDs que son correctos
    private var userSelectedPairs: MutableList<Pair<Int, Int>> = mutableListOf()

    private lateinit var btnSiguiente: Button
    private lateinit var btnComprobar: Button

    private var originalColor: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity7_juego)

        btnSiguiente = findViewById(R.id.btnSiguiente)
        btnComprobar = findViewById(R.id.btnComprobar)

        // Inicialización y configuración de los TextViews
        val tvWord1C1 = findViewById<TextView>(R.id.tvWord1C1)
        originalColor = tvWord1C1.backgroundTintList?.defaultColor
        val tvWord1C2 = findViewById<TextView>(R.id.tvWord1C2)
        val tvWord2C1 = findViewById<TextView>(R.id.tvWord2C1)
        val tvWord2C2 = findViewById<TextView>(R.id.tvWord2C2)
        val tvWord3C1 = findViewById<TextView>(R.id.tvWord3C1)
        val tvWord3C2 = findViewById<TextView>(R.id.tvWord3C2)
        val tvWord4C1 = findViewById<TextView>(R.id.tvWord4C1)
        val tvWord4C2 = findViewById<TextView>(R.id.tvWord4C2)
        val tvWord5C1 = findViewById<TextView>(R.id.tvWord5C1)
        val tvWord5C2 = findViewById<TextView>(R.id.tvWord5C2)
        // Repite para cada TextView

        setupTextViews(tvWord1C1, tvWord1C2)
        setupTextViews(tvWord2C1, tvWord2C2)
        setupTextViews(tvWord3C1, tvWord3C2)
        setupTextViews(tvWord4C1, tvWord4C2)
        setupTextViews(tvWord5C1, tvWord5C2)

        // Define los pares correctos
        correctPairs.add(Pair(R.id.tvWord1C1, R.id.tvWord2C2))
        correctPairs.add(Pair(R.id.tvWord2C1, R.id.tvWord3C2))
        correctPairs.add(Pair(R.id.tvWord3C1, R.id.tvWord1C2))
        correctPairs.add(Pair(R.id.tvWord4C1, R.id.tvWord5C2))
        correctPairs.add(Pair(R.id.tvWord5C1, R.id.tvWord4C2))

        btnComprobar.setOnClickListener {
            if (currentSelection.first != null && currentSelection.second != null) {
                checkAndProcessPair()
            }
        }

        btnSiguiente.setOnClickListener {
            intent = Intent(this, PantallaFinal::class.java) // Pasaremos a la pantalla final
            startActivity(intent)
            finish() // Finalizando esta pantalla
        }

    }

    private fun setupTextViews(vararg textViews: TextView) {
        textViews.forEach { textView ->
            textView.setOnClickListener { view ->
                onTextViewClicked(view as TextView)
            }
        }
    }

    private fun onTextViewClicked(textView: TextView) {
        if (currentSelection.first == null) {
            currentSelection = currentSelection.copy(first = textView)
            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSelected)) // Grisáceo
        } else if (currentSelection.second == null && textView.parent != currentSelection.first?.parent) {
            currentSelection = currentSelection.copy(second = textView)
            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSelected)) // Grisáceo
        }
    }


    private fun checkAndProcessPair() {
        currentSelection.first?.let { first ->
            currentSelection.second?.let { second ->
                val pairIsCorrect = correctPairs.any { it.first == first.id && it.second == second.id }
                if (pairIsCorrect) {
                    markPairAsCorrect()
                } else {
                    markPairAsIncorrect()
                }
            }
        }
    }

    private fun markPairAsIncorrect() {
        currentSelection.first?.setBackgroundColor(ContextCompat.getColor(this, R.color.colorIncorrect)) // Rojo
        currentSelection.second?.setBackgroundColor(ContextCompat.getColor(this, R.color.colorIncorrect)) // Rojo

        Handler().postDelayed({
            resetToOriginalColor(currentSelection.first)
            resetToOriginalColor(currentSelection.second)
            resetCurrentSelection()
        }, 2000)
    }

    private fun resetToOriginalColor(textView: TextView?) {
        textView?.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
    }




    private fun markPairAsCorrect() {
        currentSelection.first?.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCorrect)) // Verde
        currentSelection.second?.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCorrect)) // Verde

        userSelectedPairs.add(Pair(currentSelection.first!!.id, currentSelection.second!!.id))
        resetCurrentSelection()

        if (areAllPairsCorrect()) {
            btnSiguiente.isEnabled = true
            btnSiguiente.isClickable = true
        }
    }


    private fun resetCurrentSelection() {
        currentSelection = Pair(null, null)
    }

    private fun areAllPairsCorrect(): Boolean {
        return correctPairs.all { correctPair ->
            userSelectedPairs.any { userPair ->
                userPair.first == correctPair.first && userPair.second == correctPair.second
            }
        }
    }
}

