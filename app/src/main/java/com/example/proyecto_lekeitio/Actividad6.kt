package com.example.proyecto_lekeitio

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Actividad6 : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var buttonNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity6_principal)

        buttonNext = findViewById(R.id.btnSopaDeLetras)
        // Cambiar al nombre real del audio
        mediaPlayer = MediaPlayer.create(this, R.raw.audio_maria_diaz_haro)
    }

    fun playAudio(view: View) {
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            // Habilita el botón Siguiente una vez que el audio se haya reproducido completamente.
            buttonNext.isEnabled = true
        }
    }

    fun goToWordSearchActivity(view: View) {
        val intent = Intent(this, Actividad6SopaDeLetras::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
    }
}