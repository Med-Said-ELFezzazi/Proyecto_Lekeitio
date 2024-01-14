    package com.example.proyecto_lekeitio

    import android.content.Intent
    import android.os.Bundle
    import android.os.Handler
    import android.os.Looper
    import android.view.View
    import android.widget.Button
    import android.widget.MediaController
    import android.widget.TextView
    import android.widget.VideoView
    import androidx.appcompat.app.AppCompatActivity

    class Act3Video : AppCompatActivity() {

        //Mensaje de atención al niño
        private lateinit var txtAtencion: TextView
        private val handler = Handler(Looper.getMainLooper())
        private val runnable = object : Runnable {
            override fun run() {
                // Alterna la visibilidad del TextView para crear un efecto de parpadeo
                if (txtAtencion.visibility == View.VISIBLE) {
                    txtAtencion.visibility = View.INVISIBLE
                } else {
                    txtAtencion.visibility = View.VISIBLE
                }
                // Reprograma el Runnable para ejecutarse cada segundo
                handler.postDelayed(this, 1000) // Cambia cada segundo
            }
        }

        // Metodo para detener el handler cuando la actividad se destruya
        override fun onDestroy() {
            super.onDestroy()
            handler.removeCallbacks(runnable)
        }

        // Declaración de variables para el VideoView y el botón
        private lateinit var videoKaxarranka: VideoView
        private lateinit var btnSiguienteJuego: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_act3_video)
            // Inicialización del TextView y comienza el efecto de parpadeo
            txtAtencion = findViewById(R.id.txtAtencion)

            // Iniciar el parpadeo
            handler.post(runnable)

            // Inicialización de VideoView y Button con sus IDs de la interfaz de usuario
            videoKaxarranka = findViewById(R.id.videoKaxarranka)
            btnSiguienteJuego = findViewById(R.id.btnSiguienteJuego)

            // Configurando el MediaController
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoKaxarranka)
            videoKaxarranka.setMediaController(mediaController)

            // Establece la ruta del video y comienza la reproducción
            val path = "android.resource://" + packageName + "/" + R.raw.video_kaxarranka
            videoKaxarranka.setVideoPath(path)
            videoKaxarranka.requestFocus()
            videoKaxarranka.start()
        }

        // Método para a la siguiente actividad
        fun siguienteJuego(view: View) {
            var intent = Intent(this,Act3Juego::class.java)
            startActivityForResult(intent, 5678)
        }
    }