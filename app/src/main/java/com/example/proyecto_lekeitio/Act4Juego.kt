package com.example.proyecto_lekeitio

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class Act4Juego : AppCompatActivity() {

    private lateinit var btnComprobar: Button
    private lateinit var btnP1Verdadero: RadioButton
    private lateinit var btnP2Verdadero: RadioButton
    private lateinit var btnP3Verdadero: RadioButton
    private lateinit var btnP4Verdadero: RadioButton
    private lateinit var btnP5Verdadero: RadioButton
    private lateinit var btnP1Falso: RadioButton
    private lateinit var btnP2Falso: RadioButton
    private lateinit var btnP3Falso: RadioButton
    private lateinit var btnP4Falso: RadioButton
    private lateinit var btnP5Falso: RadioButton

    private lateinit var rgP1: RadioGroup
    private lateinit var rgP2: RadioGroup
    private lateinit var rgP3: RadioGroup
    private lateinit var rgP4: RadioGroup
    private lateinit var rgP5: RadioGroup

    private lateinit var txtPregunta1: TextView
    private lateinit var txtPregunta2: TextView
    private lateinit var txtPregunta3: TextView
    private lateinit var txtPregunta4: TextView
    private lateinit var txtPregunta5: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act4_juego)
        //Difinición de los elementos
        //RadioGroups
        rgP1 = findViewById(R.id.rgP1)
        rgP2 = findViewById(R.id.rgP2)
        rgP3 = findViewById(R.id.rgP3)
        rgP4 = findViewById(R.id.rgP4)
        rgP5 = findViewById(R.id.rgP5)

        //Bótones de respuestas
        btnComprobar = findViewById(R.id.btnComprobar)
        btnP1Verdadero = findViewById(R.id.btnP1Verdadero)
        btnP2Verdadero = findViewById(R.id.btnP2Verdadero)
        btnP3Verdadero = findViewById(R.id.btnP3Verdadero)
        btnP4Verdadero = findViewById(R.id.btnP4Verdadero)
        btnP5Verdadero = findViewById(R.id.btnP5Verdadero)
        btnP1Falso = findViewById(R.id.btnP1Falso)
        btnP2Falso = findViewById(R.id.btnP2Falso)
        btnP3Falso = findViewById(R.id.btnP3Falso)
        btnP4Falso = findViewById(R.id.btnP4Falso)
        btnP5Falso = findViewById(R.id.btnP5Falso)

        //Textfields
        txtPregunta1 = findViewById(R.id.txtPregunta1)
        txtPregunta2 = findViewById(R.id.txtPregunta2)
        txtPregunta3 = findViewById(R.id.txtPregunta3)
        txtPregunta4 = findViewById(R.id.txtPregunta4)
        txtPregunta5 = findViewById(R.id.txtPregunta5)

        //Definir un listener para cada RadioGroup
        val listenerGrupos = RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when (group.id) {
                R.id.rgP1 -> actualizarFondo(checkedId, btnP1Verdadero, btnP1Falso)
                R.id.rgP2 -> actualizarFondo(checkedId, btnP2Verdadero, btnP2Falso)
                R.id.rgP3 -> actualizarFondo(checkedId, btnP3Verdadero, btnP3Falso)
                R.id.rgP4 -> actualizarFondo(checkedId, btnP4Verdadero, btnP4Falso)
                R.id.rgP5 -> actualizarFondo(checkedId, btnP5Verdadero, btnP5Falso)
            }
            //Visibilidad del bóton comprobar
            if (verificarSeleccionEnTodosLosGrupos()){
                btnComprobar.visibility = Button.VISIBLE
            } else {
                btnComprobar.visibility = Button.INVISIBLE
            }
//'otra forma corta' btnComprobar.visibility = if (verificarSeleccionEnTodosLosGrupos()) Button.VISIBLE else Button.INVISIBLE
        }

        rgP1.setOnCheckedChangeListener(listenerGrupos)
        rgP2.setOnCheckedChangeListener(listenerGrupos)
        rgP3.setOnCheckedChangeListener(listenerGrupos)
        rgP4.setOnCheckedChangeListener(listenerGrupos)
        rgP5.setOnCheckedChangeListener(listenerGrupos)

        btnComprobar.setOnClickListener {
            val contadorCorrectas = calcularRespuestasCorrectas()
            val contadorIncorrectas = 5 - contadorCorrectas
            Toast.makeText(this, "Correctas: $contadorCorrectas Incorrectas: $contadorIncorrectas", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Metodo para cambiar las esquinas del bóton selecionado
     */
    private fun actualizarFondo(checkedId: Int, btnVerdadero: RadioButton, btnFalso: RadioButton) {
        if (checkedId == btnVerdadero.id) {
            btnVerdadero.setBackgroundResource(R.drawable.borders_verde_azul_esquinas)
            btnFalso.setBackgroundResource(R.drawable.boton_rojo)
        } else if (checkedId == btnFalso.id) {
            btnFalso.setBackgroundResource(R.drawable.borders_rojo_azul_esquinas)
            btnVerdadero.setBackgroundResource(R.drawable.boton_verde)
        }
    }

    private fun calcularRespuestasCorrectas(): Int {
        var contador = 0
        if (rgP1.checkedRadioButtonId == R.id.btnP1Verdadero){
            contador++
        }
        if (rgP2.checkedRadioButtonId == R.id.btnP2Falso) {
            contador++
        }
        if (rgP3.checkedRadioButtonId == R.id.btnP3Verdadero) {
            contador++
        }
        if (rgP4.checkedRadioButtonId == R.id.btnP4Verdadero) {
            contador++
        }
        if (rgP5.checkedRadioButtonId == R.id.btnP5Falso) {
            contador++
        }
        return contador
    }
    /**
     * Metodo que comprueba si todos los radioGroups están seleccionados
     */
    private fun verificarSeleccionEnTodosLosGrupos(): Boolean {
        return rgP1.checkedRadioButtonId != -1 &&
                rgP2.checkedRadioButtonId != -1 &&
                rgP3.checkedRadioButtonId != -1 &&
                rgP4.checkedRadioButtonId != -1 &&
                rgP5.checkedRadioButtonId != -1
    }
}