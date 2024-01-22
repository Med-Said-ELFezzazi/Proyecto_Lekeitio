package com.example.proyecto_lekeitio

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Actividad6SopaDeLetras : AppCompatActivity() {
    private lateinit var gridLayout: GridLayout
    private lateinit var buttonNext: Button
    private val gridSize = 5
    private val wordSearchLetters = arrayOf(
        arrayOf('M', 'A', 'R', 'I', 'A', 'R', 'G', 'R', 'I', 'B'),
        arrayOf('E', 'D', 'L', 'E', 'T', 'Z', 'A', 'R', 'I', 'A'),
        arrayOf('L', 'H', 'A', 'R', 'L', 'M', 'A', 'R', 'I', 'S'),
        arrayOf('V', 'D', 'O', 'L', 'O', 'R', 'E', 'S', 'R', 'T'),
        arrayOf('I', 'M', 'A', 'I', 'A', 'L', 'E', 'N', 'I', 'I'),
        arrayOf('R', 'P', 'Q', 'R', 'S', 'M', 'A', 'R', 'I', 'D'),
        arrayOf('A', 'U', 'V', 'B', 'I', 'C', 'E', 'N', 'T', 'A')
    )
    private val words = listOf("MARIA", "ELVIRA", "DOLORES", "BASTIDA", "BICENTA", "MAIALEN")
    private val wordsToClues = mutableMapOf(
        "MARIA" to "..... de Maeztu",
        "ELVIRA" to "..... Zipitria",
        "DOLORES" to "..... Ibarruri",
        "BASTIDA" to "Maria Dolores ..... Zalbidea",
        "BICENTA" to "..... Moguel",
        "MAIALEN" to "..... Lujanbio"
    )
    private lateinit var textViewClues: TextView

    private var selectedWord = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad6_juego)

        gridLayout = findViewById(R.id.gridLayout_word_search)
        buttonNext = findViewById(R.id.button_next)

        setupWordSearchGrid()
        textViewClues = findViewById(R.id.textViewClues)
        updateCluesDisplay()
    }

    private fun checkWord() {
        if (selectedWord.toString() in wordsToClues.keys) {
            // Actualiza la pista con la palabra encontrada
            wordsToClues[selectedWord.toString()] = selectedWord.toString()

            // Actualiza el TextView de pistas
            updateCluesDisplay()

            selectedWord.clear()
            if (wordsToClues.keys.all { it in words }) {
                buttonNext.isEnabled = true
            }
        }
    }

    private fun updateCluesDisplay() {
        val cluesText = wordsToClues.values.joinToString("\n")
        textViewClues.text = cluesText
    }


    private fun setupWordSearchGrid() {
        val rows = wordSearchLetters.size
        val cols = wordSearchLetters[0].size

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val textView = TextView(this)
                textView.layoutParams = GridLayout.LayoutParams(
                    GridLayout.spec(i, 1f),
                    GridLayout.spec(j, 1f)
                ).apply {
                    width = 0
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    setMargins(4, 4, 4, 4)
                }
                textView.gravity = Gravity.CENTER
                textView.textSize = 24f
                textView.text = wordSearchLetters[i][j].toString()
                textView.isClickable = true
                textView.isFocusable = true

                textView.setOnClickListener {
                    handleLetterClick(i, j)
                }
                gridLayout.addView(textView)
            }
        }
    }


    private fun handleLetterClick(row: Int, col: Int) {
        val linealIndex = row * 10 + col
        val textView = gridLayout.getChildAt(linealIndex) as TextView
        val letter = wordSearchLetters[row][col]

        // Cambiar el color de fondo o el texto para indicar selección
        if (textView.isSelected) {
            textView.isSelected = false
            textView.setBackgroundColor(Color.TRANSPARENT) // o el color original
            selectedWord.deleteCharAt(selectedWord.length - 1) // Elimina la última letra añadida
        } else {
            textView.isSelected = true
            textView.setBackgroundColor(Color.YELLOW) // Cambia a un color para indicar selección
            selectedWord.append(letter)
        }

        checkWord()
    }
}
