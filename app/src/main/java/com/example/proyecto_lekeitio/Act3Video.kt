package com.example.proyecto_lekeitio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.VideoView

class Act3Video : AppCompatActivity() {

    private lateinit var videoKaxarranka: VideoView
    private lateinit var btnSiguienteJuego: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act3_video)

        videoKaxarranka = findViewById(R.id.videoKaxarranka)
        btnSiguienteJuego = findViewById(R.id.btnSiguienteJuego)

        val path = "android.resource://" + packageName + "/" + R.raw.video_kaxarranka
        videoKaxarranka.setVideoPath(path)
        videoKaxarranka.requestFocus()
        videoKaxarranka.start()
    }

    fun siguienteJuego(view: View) {
        var intent = Intent(this,Act3Juego::class.java)
        startActivityForResult(intent, 5678)
    }
}