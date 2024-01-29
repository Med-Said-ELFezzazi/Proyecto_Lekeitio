package com.example.proyecto_lekeitio

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Actividad2 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button
    private lateinit var audioSeekBar: SeekBar
    private lateinit var resetButton: Button
    private lateinit var btnSiguiente: Button

    private val buttonToFraseText = mapOf(
        R.id.btnFrase1 to "Arrantzaleak jendea sendatzen du.",
        R.id.btnFrase2 to "Teilatuan belarra ateratzen da.",
        R.id.btnFrase3 to "Marinelak bihotzeko bat pairatzen du.",
        R.id.btnFrase4 to "Arrantzalea hondartzara doa.",
        R.id.btnFrase5 to "Beltzezko gizonak harri bihurtzen du.",
        R.id.btnFrase6 to "Beltzezko gizonak, arrantzalearekin hitz egiten du."
    )

    data class ButtonState(val buttonId: Int, var isPressed: Boolean, var selectionOrder: Int = 0, var isCorrectlySelected: Boolean = false, val fraseNum: Int)
    private val correctOrder = listOf(R.id.btnFrase3, R.id.btnFrase6, R.id.btnFrase1, R.id.btnFrase2, R.id.btnFrase4, R.id.btnFrase5)

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
        btnSiguiente = findViewById(R.id.btnSiguiente)

        // Ocultar elementos inicialmente
        frasesLayout.visibility = View.GONE
        resetButton.visibility = View.GONE
        btnSiguiente.visibility = View.GONE

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
            btnSiguiente.visibility = View.VISIBLE
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

        // Listener para el botón siguiente
        btnSiguiente.setOnClickListener {
            val intent = Intent(this, MapaActivity::class.java)
            intent.putExtra("VIDEO_ID", R.raw.mapa3)
            intent.putExtra("CONTADOR", 3)
            startActivity(intent)
            finish()
        }
    }

    private fun resetPhrases() {
        currentOrder = 1
        findViewById<TextView>(R.id.txtOrder).text = ""
        correctOrder.forEach { buttonId ->
            findViewById<Button>(buttonId).isEnabled = true
        }
        btnSiguiente.visibility = View.GONE
    }

    private fun checkOrder(): Boolean {
        if (currentOrder - 1 == correctOrder.size) {
            btnSiguiente.visibility = View.VISIBLE
            return true
        } else {
            Toast.makeText(this, "Todavía no has completado la secuencia.", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    private fun initializeButtonStates() {
        buttonStates[R.id.btnFrase1] = ButtonState(R.id.btnFrase1, false, 0, false, 1)
        buttonStates[R.id.btnFrase2] = ButtonState(R.id.btnFrase2, false, 0, false, 2)
        buttonStates[R.id.btnFrase3] = ButtonState(R.id.btnFrase3, false, 0, false, 3)
        buttonStates[R.id.btnFrase4] = ButtonState(R.id.btnFrase4, false, 0, false, 4)
        buttonStates[R.id.btnFrase5] = ButtonState(R.id.btnFrase5, false, 0, false, 5)
        buttonStates[R.id.btnFrase6] = ButtonState(R.id.btnFrase6, false, 0, false, 6)
    }

    private fun setupPhraseButtonListeners() {
        val phraseButtons = listOf(R.id.btnFrase1, R.id.btnFrase2, R.id.btnFrase3, R.id.btnFrase4, R.id.btnFrase5, R.id.btnFrase6)
        phraseButtons.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener { onPhraseButtonClicked(buttonId) }
        }
    }

    private fun onPhraseButtonClicked(buttonId: Int) {
        if (currentOrder <= correctOrder.size && buttonId == correctOrder[currentOrder - 1]) {
            val phraseTextView = findViewById<TextView>(R.id.txtOrder)
            val buttonText = "Frase ${buttonStates[buttonId]?.fraseNum}"
            val currentText = phraseTextView.text.toString()
            phraseTextView.text = if (currentText.isEmpty()) buttonText else "$currentText, $buttonText"
            findViewById<Button>(buttonId).isEnabled = false
            currentOrder++

            // Habilitar el botón "Comprobar" si todas las frases han sido seleccionadas
            if (currentOrder > correctOrder.size) {
                btnSiguiente.isEnabled = true
            }
        } else {
            val correctButtonId = correctOrder[currentOrder - 1]
            val correctFrase = buttonToFraseText[correctButtonId]
            val incorrectFrase = buttonToFraseText[buttonId]
            Toast.makeText(this, "La frase '$incorrectFrase' no va en la posición $currentOrder.", Toast.LENGTH_SHORT).show()
        }
    }
}