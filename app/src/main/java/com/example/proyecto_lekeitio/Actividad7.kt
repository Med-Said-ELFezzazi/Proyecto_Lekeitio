package com.example.proyecto_lekeitio

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.SeekBar

class Actividad7 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var seekBar: SeekBar
    private lateinit var videoLinkButton: Button
    private lateinit var toGameButton: Button

    private val handler = Handler()
    private val updateSeekBar: Runnable = object : Runnable {
        override fun run() {
            if (mediaPlayer.isPlaying) {
                seekBar.progress = mediaPlayer.currentPosition
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity7_principal)

        // Inicialización de los componentes
        mediaPlayer = MediaPlayer.create(this, R.raw.euskalkiak)
        playButton = findViewById(R.id.btnPlay)
        pauseButton = findViewById(R.id.btnPause)
        seekBar = findViewById(R.id.seekBarAudio)
        videoLinkButton = findViewById(R.id.btnVerVideo)
        toGameButton = findViewById(R.id.btnToGame)

        // Configurar el botón del enlace al video
        videoLinkButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://eu.wikipedia.org/wiki/Fitxategi:Lekeitioko_Mertxe_Pagoaga_2.webm"))
            startActivity(intent)
        }

        toGameButton.setOnClickListener {
            val intent = Intent(this, Actividad7Juego::class.java)
            startActivity(intent)
            finish()
        }

        // Configurar los botones de reproducción de audio
        playButton.setOnClickListener {
            mediaPlayer.start()
            handler.post(updateSeekBar)
        }
        pauseButton.setOnClickListener {
            mediaPlayer.pause()
            handler.removeCallbacks(updateSeekBar)
        }

        // Actualizar la SeekBar con el progreso del audio
        mediaPlayer.setOnPreparedListener { mp ->
            seekBar.max = mp.duration
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) mediaPlayer.seekTo(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
        }

        // Hacer visible el botón para ir a la Actividad7Juego cuando el audio termine
        mediaPlayer.setOnCompletionListener {
            toGameButton.visibility = Button.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
        handler.removeCallbacks(updateSeekBar)
    }
}
