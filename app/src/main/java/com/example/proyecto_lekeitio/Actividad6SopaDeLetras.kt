package com.example.proyecto_lekeitio

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Actividad6SopaDeLetras : AppCompatActivity() { // Extiende AppCompatActivity
    private lateinit var gridLayout: GridLayout
    private lateinit var buttonNext: Button
    private val gridSize = 5
    private val wordSearchLetters = arrayOf(
        arrayOf('M', 'A', 'R', 'I', 'A'),
        arrayOf('D', 'I', 'A', 'Z', 'D'),
        arrayOf('E', 'H', 'A', 'R', 'O'),
        arrayOf('L', 'P', 'Q', 'R', 'S'),
        arrayOf('T', 'U', 'V', 'W', 'X')
    )
    private var selectedWord = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad6_juego)

        gridLayout = findViewById(R.id.gridLayout_word_search)
        buttonNext = findViewById(R.id.button_next)

        setupWordSearchGrid()
    }

    private fun setupWordSearchGrid() {
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                val textView = TextView(this)
                textView.layoutParams = GridLayout.LayoutParams(GridLayout.spec(i, 1f), GridLayout.spec(j, 1f)).apply {
                    width = 0
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    setMargins(4, 4, 4, 4)
                }
                textView.gravity = Gravity.CENTER
                textView.textSize = 24f
                textView.text = wordSearchLetters[i][j].toString()
                textView.setOnClickListener {
                    handleLetterClick(i, j)
                }
                gridLayout.addView(textView)
            }
        }
    }

    private fun handleLetterClick(row: Int, col: Int) {
        val letter = wordSearchLetters[row][col]
        selectedWord.append(letter)
        // Here you would handle the logic to check if the selected letters form a word
        checkWord()
    }

    private fun checkWord() {
        // Dummy check for the word 'MARIA'
        if (selectedWord.toString() == "MARIA") {
            Toast.makeText(this, "Palabra encontrada: $selectedWord", Toast.LENGTH_SHORT).show()
            selectedWord.clear()
            // Enable next button if all words are found
            // For now, we just enable it after finding 'MARIA'
            buttonNext.isEnabled = true
        }
    }
}
