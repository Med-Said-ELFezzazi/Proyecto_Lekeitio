package com.example.proyecto_lekeitio

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class Actividad6SopaDeLetras : AppCompatActivity() {
    private lateinit var gridLayout: GridLayout
    private lateinit var buttonNext: Button
    private var lastSelectedRow: Int? = null
    private var lastSelectedCol: Int? = null
    private val foundLetterIndices = mutableSetOf<Int>()
    private val foundWords = mutableSetOf<String>()
    private val gridSize = 5
    private val wordSearchLetters = arrayOf(
        arrayOf('M', 'A', 'R', 'I', 'A', 'R', 'G', 'R', 'I', 'B'),
        arrayOf('E', 'D', 'L', 'E', 'T', 'Z', 'A', 'R', 'I', 'A'),
        arrayOf('L', 'H', 'A', 'R', 'L', 'M', 'A', 'R', 'I', 'S'),
        arrayOf('V', 'D', 'O', 'L', 'O', 'R', 'E', 'S', 'R', 'T'),
        arrayOf('I', 'M', 'A', 'I', 'A', 'L', 'E', 'N', 'I', 'I'),
        arrayOf('R', 'P', 'Q', 'R', 'S', 'M', 'A', 'R', 'I', 'D'),
        arrayOf('A', 'S', 'A', 'I', 'N', 'L', 'E', 'N', 'I', 'A'),
        arrayOf('R', 'P', 'Q', 'R', 'S', 'M', 'A', 'R', 'I', 'D'),
        arrayOf('Q', 'S', 'H', 'B', 'P', 'T', 'E', 'S', 'I', 'Z'),
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
    private var gridEnabled = true
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

        buttonNext.setOnClickListener {
            val intent = Intent(this, MapaActivity::class.java)
            intent.putExtra("VIDEO_ID", R.raw.mapa7)
            intent.putExtra("CONTADOR", 7)
            startActivity(intent)
            finish()
        }
    }


    private fun checkWord() {
        val currentWord = selectedWord.toString()

        if (currentWord in words && currentWord !in foundWords) {
            foundWords.add(currentWord)
            addFoundWordIndices(currentWord) // Agrega los índices de las letras de la palabra encontrada
            selectedWord.clear()
            updateCluesDisplay()

            if (foundWords.size == words.size) {
                buttonNext.isEnabled = true
            }
        }
    }



    private fun updateCluesDisplay() {
        val cluesText = wordsToClues.entries.joinToString("\n") { (word, clue) ->
            if (word in foundWords) word else clue
        }
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

    private fun addFoundWordIndices(word: String) {
        for (i in wordSearchLetters.indices) {
            for (j in 0 until wordSearchLetters[i].size - word.length + 1) {
                var found = true
                for (k in word.indices) {
                    if (wordSearchLetters[i][j + k] != word[k]) {
                        found = false
                        break
                    }
                }

                if (found) {
                    for (k in word.indices) {
                        val linearIndex = i * 10 + j + k
                        foundLetterIndices.add(linearIndex)
                    }
                    return
                }
            }
        }
    }


    private fun handleLetterClick(row: Int, col: Int) {
        val linearIndex = row * 10 + col

        // Permitir selección si la letra actual no está en foundLetterIndices
        if (!foundLetterIndices.contains(linearIndex)) {
            val textView = gridLayout.getChildAt(linearIndex) as TextView

            if (lastSelectedRow == null || isAdjacent(row, col, lastSelectedRow!!, lastSelectedCol!!)) {
                // Si no hay selección previa o la letra es adyacente a la última seleccionada
                if (textView.isSelected) {
                    // Desmarcar la selección actual si ya está seleccionada
                    textView.isSelected = false
                    textView.setBackgroundColor(Color.TRANSPARENT)
                    selectedWord.deleteCharAt(selectedWord.length - 1)
                } else {
                    // Seleccionar la nueva letra
                    textView.isSelected = true
                    textView.setBackgroundColor(Color.YELLOW)
                    selectedWord.append(textView.text)
                    lastSelectedRow = row
                    lastSelectedCol = col
                }
            } else {
                // Reiniciar la selección si la letra no es adyacente
                resetSelection()
                handleLetterClick(row, col) // Intenta seleccionar de nuevo
            }
        }

        checkWord()
    }


    private fun isAdjacent(row: Int, col: Int, lastRow: Int, lastCol: Int): Boolean {
        return abs(row - lastRow) <= 1 && abs(col - lastCol) <= 1
    }

    private fun resetSelection() {
        for (i in 0 until gridLayout.childCount) {
            val child = gridLayout.getChildAt(i) as TextView
            if (child.isSelected) {
                child.isSelected = false
                child.setBackgroundColor(Color.TRANSPARENT) // o el color original
            }
        }
        selectedWord.clear()
        lastSelectedRow = null
        lastSelectedCol = null
    }
}
