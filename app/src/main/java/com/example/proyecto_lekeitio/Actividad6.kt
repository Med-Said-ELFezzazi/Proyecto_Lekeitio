package com.example.proyecto_lekeitio

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.SeekBar

class Actividad6 : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var buttonPlay: Button
    private lateinit var buttonPause: Button
    private lateinit var buttonStop: Button
    private lateinit var seekBar: SeekBar
    private lateinit var buttonNext: Button

    private val handler = Handler()
    private val updateSeekBar = object : Runnable {
        override fun run() {
            if (mediaPlayer.isPlaying) {
                seekBar.progress = mediaPlayer.currentPosition
                handler.postDelayed(this, 1000) // Actualiza cada segundo
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity6_principal)

        buttonPlay = findViewById(R.id.button_play_audio)
        buttonPause = findViewById(R.id.button_pause_audio)
        buttonStop = findViewById(R.id.button_stop_audio)
        seekBar = findViewById(R.id.seekBar)

        mediaPlayer = MediaPlayer.create(this, R.raw.maria_diaz_de_haro)

        buttonPlay.setOnClickListener { playAudio() }
        buttonPause.setOnClickListener { pauseAudio() }
        buttonStop.setOnClickListener { stopAudio() }

        // Inicializar SeekBar y gestionar los eventos de cambio
        seekBar.max = mediaPlayer.duration
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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

        buttonNext = findViewById(R.id.btnSopaDeLetras)
        buttonNext.setOnClickListener { goToWordSearchActivity() }

        // Deshabilitar el botón hasta que se cumpla alguna condición (opcional)
        buttonNext.isEnabled = false

        mediaPlayer.setOnCompletionListener {
            // Habilitar el botón Siguiente cuando el audio termine
            buttonNext.isEnabled = true
        }
    }

    private fun goToWordSearchActivity() {
        val intent = Intent(this, Actividad6SopaDeLetras::class.java)
        startActivity(intent)
    }

    private fun playAudio() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
            handler.post(updateSeekBar) // Inicia la actualización del SeekBar
        }
    }

    private fun pauseAudio() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            handler.removeCallbacks(updateSeekBar) // Detiene la actualización del SeekBar
        }
    }

    private fun stopAudio() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.prepare()
            handler.removeCallbacks(updateSeekBar) // Detiene la actualización del SeekBar
        }
        mediaPlayer.seekTo(0)
        seekBar.progress = 0
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
    }
}
