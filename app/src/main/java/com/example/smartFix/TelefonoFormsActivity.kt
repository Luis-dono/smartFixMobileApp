package com.example.smartFix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts


class TelefonoFormsActivity : AppCompatActivity(){
    lateinit var aggrep:Button
    lateinit var btnverReparaciones: Button
    lateinit var progBar: ProgressBar
    lateinit var tvProgress:TextView

    private val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
        if (activityResult.resultCode == RESULT_OK){
            val estatusid = activityResult.data?.getIntExtra("estatusid",0)
            cambiarProgressBar(estatusid!!)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telefono_forms)
        progBar = findViewById(R.id.progressBar)
        progBar.max = 5
        btnverReparaciones=findViewById(R.id.btnVerReparaciones)
        aggrep=findViewById(R.id.btnagregarreparacion)
        tvProgress = findViewById(R.id.tvProgreso)
        var bundle: Bundle  = intent.extras!!
        val estatusid = bundle?.getInt("estatusid")
        if(estatusid== 3){
            btnverReparaciones.isEnabled = true
        }
        cambiarProgressBar(estatusid)
        llenarcasillas(bundle);
        aggrep.setOnClickListener {
            enviarAReps(bundle)
        }
    }

    fun enviarAReps(bundle:Bundle){
        val intent = Intent(this@TelefonoFormsActivity,reparacionesActivity::class.java)
        var tecnicoid: Int? = bundle?.getInt("tecnicoid")
        var folio: String? = bundle?.getString("folio")
        var modeloid: Int? = bundle?.getInt("modeloid")
        intent.putExtra("tecnicoid",tecnicoid)
        intent.putExtra("folio",folio)
        intent.putExtra("modeloid",modeloid)
        responseLauncher.launch(intent)
    }

    fun cambiarProgressBar(estatusid:Int){
        progBar.progress = estatusid
        when(estatusid){
            2 -> tvProgress.text = "En diagnostico"
            3 -> tvProgress.text = "Por confirmar"
            4 -> tvProgress.text = "Reparando"
            5 -> tvProgress.text = "Listo"
            else -> tvProgress.text = "No aplica"
        }

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