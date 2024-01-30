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
import android.widget.TextView

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController

class Act3Activity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    private lateinit var btnSiguienteVideo: Button
    //private lateinit var btnPlayAudio: Button

    private lateinit var imgPlayAudio: ImageView
    private lateinit var seekBarAudio: SeekBar
    private val handler = Handler()

    private var estabaPlayAntes: Boolean = false //Variable para controlar el estado del audio (play/pause)

    private lateinit var txtTiempoActual: TextView      //textviews de la duracion de reproducción
    private lateinit var txtTiempoTotal: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act3)

        //El audio querido
        mp = MediaPlayer.create(this, R.raw.kaxarranka)

        btnSiguienteVideo = findViewById(R.id.btnSiguienteVideo)

        imgPlayAudio = findViewById(R.id.imgPlayAudio)
        seekBarAudio = findViewById(R.id.seekBarAudio)

        txtTiempoActual = findViewById(R.id.currentTimeTextView)
        txtTiempoTotal = findViewById(R.id.totalTimeTextView)

        seekBarAudio.max = mp.duration
        btnSiguienteVideo.isVisible = false


        /**
         * Actualiza el SeekBar para reflejar la posición actual del audio
         */
        txtTiempoTotal.text =  formateoTiempo(mp.duration)
        val updateSeekBar: Runnable = object : Runnable {
            override fun run() {
                if (mp.isPlaying) {
                    seekBarAudio.progress = mp.currentPosition
                    // actualizar el tiempo en el text view
                    txtTiempoActual.text = formateoTiempo(mp.currentPosition)
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
                //Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show()
            } else {
                // Si el audio no está reproduciéndose, iniciarlo o reanudarlo
                mp.start()

                imgPlayAudio.setImageResource(R.drawable.pause_debujo) // Cambiar a imagen de pause
                handler.postDelayed(updateSeekBar, 0)

                //Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show()
            }
            // Actualizar la visibilidad del botón btnSiguienteVideo según el estado de reproducción
            //btnSiguiente.isVisible = true
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

        //cuando se reproduce el audio hasta el final
        mp.setOnCompletionListener {
            // Habilitar el botón Siguiente cuando el audio termine
            imgPlayAudio.setImageResource(R.drawable.play_debujo)
            btnSiguienteVideo.isVisible = true
        }

        btnSiguienteVideo.setOnClickListener{
            pasarAlVideo()
        }
    }

    /**
     * Metodo que ajusta el tiempo en minutos y segundos
     */
    fun formateoTiempo(milliseconds: Int): String {
        val minutes = (milliseconds / 1000) / 60
        val seconds = (milliseconds / 1000) % 60
        return String.format("%d:%02d", minutes, seconds)
    }

    private fun pasarAlVideo() {
        val intent = Intent(this, Act3Video::class.java)
        startActivity(intent)
        // Finalizar la actividad actual
        finish()
    }


    override fun onPause() {
        super.onPause()
        if (mp.isPlaying) {
            mp.pause() // Pausa el audio cuando la actividad está pausada
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.release() // Libera los recursos del MediaPlayer al destruir la actividad
    }

}