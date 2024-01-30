package com.example.proyecto_lekeitio

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
//Clase de la pantalla final
class PantallaFinal : AppCompatActivity() {
    private lateinit var mp: MediaPlayer
    private lateinit var btnVolverMapa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gif_felicitaciones_final)

        btnVolverMapa = findViewById(R.id.btnVolverMapa)
        btnVolverMapa.visibility = View.GONE  // El botón inicia como invisible

        // Audio
        mp = MediaPlayer.create(this, R.raw.audio_felicitacion_final)

        // Una vez cargada la pantalla, se lanza el audio
        mp.start()

        // Hacer que el botón aparezca después de 3 segundos
        Handler().postDelayed({
            btnVolverMapa.visibility = View.VISIBLE
        }, 3000)  // 3000 milisegundos = 3 segundos

        // Acabar con la pantalla final y volver
        btnVolverMapa.setOnClickListener {
            val intent = Intent(this, MapaActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
