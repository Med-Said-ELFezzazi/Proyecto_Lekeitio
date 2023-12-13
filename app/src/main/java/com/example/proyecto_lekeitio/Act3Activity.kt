package com.example.proyecto_lekeitio

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController

class Act3Activity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    private lateinit var btnPlayAudio: Button
    private lateinit var btnSiguienteVideo: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act3)

        mp = MediaPlayer.create(this, R.raw.kaxarranka)
        btnPlayAudio = findViewById(R.id.btnPlayAudio)
        btnSiguienteVideo = findViewById(R.id.btnSiguienteVideo)

        btnSiguienteVideo.isVisible = false //poner el boton invisible al principio

        btnPlayAudio.setOnClickListener {
            if (mp.isPlaying) {
                // Si el audio está reproduciéndose, pausarlo
                mp.pause()
                Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show()
            } else {
                // Si el audio no está reproduciéndose, iniciarlo o reanudarlo
                mp.start()
                Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show()

            }
            // Actualizar la visibilidad del botón btnSiguienteVideo según el estado de reproducción
            btnSiguienteVideo.isVisible = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Liberar recursos del MediaPlayer al destruir la actividad (parar el audio)
        mp.release()
    }
    fun playAudio(view: View) {
        mp.start()
        Toast.makeText(this,"Play",Toast.LENGTH_SHORT).show()
    }


    fun pasarAlVideo(view: View) {
        //Depués de hacer click en siguiente , el audio debe parar
        mp.release()
        //Pasar a la siguiente pantalla
        var intent = Intent(this,Act3Video::class.java)
        startActivityForResult(intent, 5678)
    }

}