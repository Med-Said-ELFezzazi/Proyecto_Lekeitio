package com.example.proyecto_lekeitio

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button

import android.widget.ImageView
import android.widget.SeekBar

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController

class Act3Activity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    private lateinit var btnSiguienteVideo: Button
    private lateinit var btnPlayAudio: Button

    private lateinit var imgPlayAudio: ImageView
    private lateinit var seekBarAudio: SeekBar
    private val handler = Handler()

    private var estabaPlayAntes: Boolean = false //Variable para controlar el estado del audio (play/pause)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act3)

        //El audio querido
        mp = MediaPlayer.create(this, R.raw.kaxarranka)

        btnSiguienteVideo = findViewById(R.id.btnSiguienteVideo)
        btnSiguienteVideo.isVisible = false //poner el bóton soguiente invisible al principio

        imgPlayAudio = findViewById(R.id.imgPlayAudio)
        seekBarAudio = findViewById(R.id.seekBarAudio)
        btnSiguienteVideo = findViewById(R.id.btnSiguienteVideo)

        seekBarAudio.max = mp.duration

        /**
         * Actualiza el SeekBar para reflejar la posición actual del audio.
         */
        val updateSeekBar: Runnable = object : Runnable {
            override fun run() {
                if (mp.isPlaying) {
                    seekBarAudio.progress = mp.currentPosition
                    handler.postDelayed(this, 1000)
                }
            }
        }


        //Funcionamiento del bóton del audio
        imgPlayAudio.setOnClickListener {
            if (mp.isPlaying) {
                // Si el audio está reproduciéndose, pausarlo
                mp.pause()

                imgPlayAudio.setImageResource(R.drawable.play_debujo) // Cambiar a imagen de play
                handler.removeCallbacks(updateSeekBar)

                Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show()
            } else {
                // Si el audio no está reproduciéndose, iniciarlo o reanudarlo
                mp.start()

                imgPlayAudio.setImageResource(R.drawable.pause_debujo) // Cambiar a imagen de pause
                handler.postDelayed(updateSeekBar, 0)

                Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show()
            }
            // Actualizar la visibilidad del botón btnSiguienteVideo según el estado de reproducción
            btnSiguienteVideo.isVisible = true
        }
        seekBarAudio.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            //Cuando el progreso se cambia
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mp.seekTo(progress)
                }
            }

            //Cuando el user esta arrastrando la barra
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Guarda el estado de reproducción y pausa si está en reproducción
                estabaPlayAntes = mp.isPlaying
                if (estabaPlayAntes) {      //si esta en play
                    mp.pause()              // Pausar el audio
                }
            }

            //Cuando el user suelta la barra
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (estabaPlayAntes) {
                    mp.start()
                    handler.postDelayed(updateSeekBar, 0)
                }
            }
        })

    }


    override fun onDestroy() {      //ESOS DOS METODOS CREO Q NO SON NECESARIOS LO QUE HACEN ES PLAY Y PAUSE EL AUDIO
        super.onDestroy()
        // Liberar recursos del MediaPlayer al destruir la actividad (parar el audio)
        mp.release()
    }

    fun pasarAlVideo(view: View) {
        //Depués de hacer click en siguiente , el audio debe parar
        mp.release()
        //Pasar a la siguiente pantalla
        var intent = Intent(this,Act3Video::class.java)
        startActivityForResult(intent, 5678)

        //Acabar con esa pantalla
        finish()
    }

}