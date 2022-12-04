package com.example.smartFix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class TelefonoFormsActivity : AppCompatActivity(){
    lateinit var aggrep:Button
    lateinit var RepPend:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telefono_forms)
        RepPend=findViewById(R.id.REPpendientes)
        aggrep=findViewById(R.id.btnagregarreparacion)
        var bundle: Bundle  = intent.extras!!
        llenarcasillas(bundle);
        aggrep.setOnClickListener {
            enviarAReps(bundle)
        }
        RepPend.setOnClickListener {
            val intent = Intent(this@TelefonoFormsActivity,manejoreparacionesActivity::class.java)
            var folioaux: String? =bundle.getString("folio")
            intent.putExtra("folio",folioaux)
            startActivity(intent)
        }

    }
    fun enviarAReps(bundle:Bundle){
        val intent = Intent(this@TelefonoFormsActivity,reparacionesActivity::class.java)
        var tecnicoid: Int? = bundle?.getInt("tecnicoid")
        var folio: String? = bundle?.getString("folio")
        intent.putExtra("tecnicoid",tecnicoid)
        intent.putExtra("folio",folio)
        startActivity(intent)
    }
    fun llenarcasillas(bundle:Bundle){
        var marca:EditText=findViewById(R.id.editText2)
        var modelo:EditText=findViewById(R.id.editTextmarca)
        var color:EditText=findViewById(R.id.editText3)
        var descripcion:EditText=findViewById(R.id.editText4)


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
//1c798653
//e798e01f