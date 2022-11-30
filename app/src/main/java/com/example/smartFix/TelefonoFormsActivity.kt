package com.example.smartFix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class TelefonoFormsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telefono_forms)
        llenarcasillas();
    }
    fun llenarcasillas(){
        var marca:EditText=findViewById(R.id.editText2)
        var modelo:EditText=findViewById(R.id.editText)
        var color:EditText=findViewById(R.id.editText3)
        var descripcion:EditText=findViewById(R.id.editText4)
        val bundle: Bundle? = intent.extras
        var incmarca: String? = bundle?.getString("marca")
        var incdescripcion: String?= bundle?.getString("descripcion")
        var inccolor: String?= bundle?.getString("color")
        var incmodelo: String?= bundle?.getString("modelo")
        marca.setText(incmarca)
        modelo.setText(incmodelo)
        color.setText(inccolor)
        descripcion.setText(incdescripcion)

    }
}