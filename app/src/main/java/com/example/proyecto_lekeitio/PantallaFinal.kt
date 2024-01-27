package com.example.proyecto_lekeitio

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PantallaFinal : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gif_felicitaciones_final)

        var btnVolverMapa : Button = findViewById(R.id.btnVolverMapa)
        //Audio
        mp = MediaPlayer.create(this, R.raw.audio_felicitacion_final)

        //Una vez cargada la pantalla , se lanza el audio
        mp.start()

        btnVolverMapa.setOnClickListener{
            finish()
        }
    }
}