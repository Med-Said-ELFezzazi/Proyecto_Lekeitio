package com.example.proyecto_lekeitio

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.view.isVisible

class Act3Activity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    private lateinit var btnPlayAudio: Button
    private lateinit var btnSiguienteVideo: Button
    private lateinit var seekBarAudio: SeekBar
    private val handler = Handler()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act3)

        mp = MediaPlayer.create(this, R.raw.kaxarranka)
        btnPlayAudio = findViewById(R.id.btnPlayAudio)
        btnSiguienteVideo = findViewById(R.id.btnSiguienteVideo)
        seekBarAudio = findViewById(R.id.seekBarAudio)

        seekBarAudio.max = mp.duration

        btnPlayAudio.setOnClickListener {
            if (mp.isPlaying) {
                mp.pause()
                handler.removeCallbacks(updateSeekBar)
                Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show()
            } else {
                mp.start()
                handler.postDelayed(updateSeekBar, 0)
                Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show()
            }
            btnSiguienteVideo.isVisible = true
        }

        seekBarAudio.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mp.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Opcional
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Opcional
            }
        })
    }

    private val updateSeekBar: Runnable = object : Runnable {
        override fun run() {
            if (mp.isPlaying) {
                seekBarAudio.progress = mp.currentPosition
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mp.isPlaying) {
            mp.stop()
        }
        mp.release()
        handler.removeCallbacks(updateSeekBar)
    }
}

/*
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
*/
