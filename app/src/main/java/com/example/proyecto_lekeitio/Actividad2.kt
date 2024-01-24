package com.example.proyecto_lekeitio

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Actividad2 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button
    private lateinit var audioSeekBar: SeekBar
    private lateinit var resetButton: Button
    private lateinit var checkButton: Button
    private lateinit var nextButton: Button

    data class ButtonState(val buttonId: Int, var isPressed: Boolean, var position: Int, var isCorrectlySelected: Boolean = false)

    private val buttonStates = mutableMapOf<Int, ButtonState>()
    private var currentOrder = 1

    private val handler = Handler()
    private val updateSeekBar = object : Runnable {
        override fun run() {
            if (mediaPlayer.isPlaying) {
                audioSeekBar.progress = mediaPlayer.currentPosition
                handler.postDelayed(this, 1000) // Actualiza cada segundo
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)

        // Inicialización de los controles
        playButton = findViewById(R.id.playBtn)
        pauseButton = findViewById(R.id.pararBtn)
        stopButton = findViewById(R.id.detenerBtn)
        audioSeekBar = findViewById(R.id.barraAudio)
        val frasesLayout = findViewById<LinearLayout>(R.id.frasesLayout)
        resetButton = findViewById(R.id.btnReiniciar)
        checkButton = findViewById(R.id.btnComprobar)
        nextButton = findViewById(R.id.btnSiguiente)

        // Ocultar elementos inicialmente
        frasesLayout.visibility = View.GONE
        resetButton.visibility = View.GONE
        checkButton.visibility = View.GONE
        nextButton.visibility = View.GONE

        initializeButtonStates()
        setupPhraseButtonListeners()

        // Configuración del MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.aititxa_makurra)
        audioSeekBar.max = mediaPlayer.duration

        // Listeners para los botones de control del audio
        playButton.setOnClickListener { mediaPlayer.start() }
        pauseButton.setOnClickListener { mediaPlayer.pause() }
        stopButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.prepare()
            mediaPlayer.seekTo(0)
        }

        mediaPlayer.setOnCompletionListener {
            frasesLayout.visibility = View.VISIBLE
            resetButton.visibility = View.VISIBLE
            checkButton.visibility = View.VISIBLE
        }

        // Listener para la barra de progreso del audio
        audioSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.progress?.let { progress ->
                    mediaPlayer.seekTo(progress)
                    mediaPlayer.start()
                }
            }
        })



        // Listener para el botón de reset
        resetButton.setOnClickListener {
            resetPhrases()
        }

        // Listener para el botón de comprobar
        checkButton.setOnClickListener {
            if (checkOrder()) {
                nextButton.visibility = View.VISIBLE
            } else {
                // Opcional: Mensaje de error o lógica adicional
            }
        }

        // Listener para el botón siguiente
        nextButton.setOnClickListener {
            // Aquí va la lógica para ir a la siguiente pantalla o actividad
        }
    }

    private fun resetPhrases() {
        currentOrder = 1
        buttonStates.keys.forEach { buttonId ->
            val buttonState = buttonStates[buttonId]
            if (buttonState != null && !buttonState.isCorrectlySelected) {
                val button = findViewById<Button>(buttonId)
                button.isEnabled = true
                buttonState.isPressed = false
                buttonState.position = 0
            }
        }
        // Ocultar el botón "Siguiente" si es necesario
        nextButton.visibility = View.GONE
    }


    private fun checkOrder(): Boolean {
        var isCorrect = true
        val correctOrder = listOf(R.id.btnFrase1, R.id.btnFrase2, R.id.btnFrase3)
        val unselectedButtons = buttonStates.filter { !it.value.isCorrectlySelected && !it.value.isPressed }
        val incorrectlySelectedButtons = buttonStates.filter { !it.value.isCorrectlySelected && it.value.isPressed }


        if (unselectedButtons.size == 1 && incorrectlySelectedButtons.isEmpty()) {
            val lastButtonState = unselectedButtons.values.first()
            lastButtonState.isCorrectlySelected = true
            val lastButton = findViewById<Button>(lastButtonState.buttonId)
            lastButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCorrect))
        }

        buttonStates.values.sortedBy { it.position }.forEachIndexed { index, buttonState ->
            val button = findViewById<Button>(buttonState.buttonId)
            if (buttonState.buttonId == correctOrder[index]) {
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCorrect))
                buttonState.isCorrectlySelected = true
            } else {
                if (!buttonState.isCorrectlySelected) {
                    button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorIncorrect))
                    button.isEnabled = true // Habilitar nuevamente el botón solo si no fue seleccionado correctamente antes
                }
                isCorrect = false
            }
        }

        return isCorrect
    }


    private fun initializeButtonStates() {
        buttonStates[R.id.btnFrase1] = ButtonState(R.id.btnFrase1, false, 0)
        buttonStates[R.id.btnFrase2] = ButtonState(R.id.btnFrase2, false, 0)
        buttonStates[R.id.btnFrase3] = ButtonState(R.id.btnFrase3, false, 0)
    }

    private fun setupPhraseButtonListeners() {
        val phraseButtons = listOf(R.id.btnFrase1, R.id.btnFrase2, R.id.btnFrase3)
        phraseButtons.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener { onPhraseButtonClicked(buttonId) }
        }
    }

    private fun onPhraseButtonClicked(buttonId: Int) {
        val state = buttonStates[buttonId]
        state?.let {
            it.isPressed = true
            it.position = currentOrder++
            findViewById<Button>(buttonId).isEnabled = false
        }
    }
}
