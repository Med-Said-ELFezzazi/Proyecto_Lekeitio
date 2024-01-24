package com.example.proyecto_lekeitio

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.isVisible

class EnunAct5Activity : AppCompatActivity() {

    private lateinit var btnVideo:Button
    private lateinit var btnSiguiente:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enun_act5)

        btnVideo = findViewById(R.id.videoAct5)
        btnSiguiente = findViewById(R.id.btnSiguienteAct5)

        btnSiguiente.isVisible = false

    }

    fun siguiente(view: View) {
        val intent = Intent(this, Pregunta1Act5Activity::class.java)
        startActivity(intent)
        finish()
    }

    fun mostrarVideo(view: View) {
        val youtubeVideoId:String ="Ao26rDejfvQ" //Id video.
        val appIntent:Intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeVideoId))
        try{
            startActivity(appIntent) //Abre con aplicación.
        }catch(ex: ActivityNotFoundException){
            //En caso de no existir la aplicación instalada se abre mediante el navegador.
            val webIntent:Intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + youtubeVideoId))
            startActivity(webIntent)
        }
        btnSiguiente.isVisible = true
    }

    /*String youtubeVideoId = "t6LbzHMDVUg"; //Id video.
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeVideoId));
        try {
            startActivity(appIntent);  //Abre con aplicación.
        } catch (ActivityNotFoundException ex) {
            //En caso de no existir la aplicación instalada se abre mediante el navegador.
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + youtubeVideoId));
            startActivity(webIntent);
    }*/
}