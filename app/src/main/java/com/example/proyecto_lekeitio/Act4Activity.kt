package com.example.proyecto_lekeitio

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class Act4Activity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    private lateinit var btnSiguiente: Button

    private lateinit var imgPlayAudio: ImageView
    private lateinit var seekBarAudio: SeekBar
    private val handler = Handler()

    private var estabaPlayAntes: Boolean = false //Variable para controlar el estado del audio (play/pause)


    private lateinit var txtTiempoActual: TextView      //textviews de la duracion de reproducción
    private lateinit var txtTiempoTotal: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act4)

        //El audio querido
        mp = MediaPlayer.create(this, R.raw.saregileak)

        btnSiguiente = findViewById(R.id.btnSiguiente)
        imgPlayAudio = findViewById(R.id.imgPlayAudio)
        seekBarAudio = findViewById(R.id.seekBarAudio)

        //btnSiguiente.isVisible = false //poner el botón soguiente invisible al principio

        seekBarAudio.max = mp.duration
        btnSiguiente.isVisible = false

        //Funcionamiento de tiempo de reporducción
        txtTiempoActual = findViewById(R.id.currentTimeTextView)
        txtTiempoTotal = findViewById(R.id.totalTimeTextView)

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
            btnSiguiente.isVisible = true
        }
        btnSiguiente.setOnClickListener{
            pasarAlJuego()
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

    /**
     * Metodo que lleva a la siguiente pantalla 'la actividad4'
     * y detiene el audio si está en reproducción.
     */
    private fun pasarAlJuego() {
       // mp.release()  Eso me generaria errors
        // Pasar a la siguiente pantalla
        val intent = Intent(this, Act4Juego::class.java)
        startActivity(intent)

        // Finalizar la actividad actual
        finish()
    }

}