package com.example.smartFix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.smartFix.apiRetrofit.SfApi
import com.example.smartFix.apiRetrofit.models.AsignacionTecnicoResponse
import com.example.smartFix.apiRetrofit.models.Detalle
import com.example.smartFix.apiRetrofit.models.PatchData
import com.example.smartFix.apiRetrofit.models.TelefonData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var btnFolio: Button

class FolioTelefonoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_folio_telefono)
        btnFolio = findViewById(R.id.btnFolio)
        btnFolio.setOnClickListener {
            Log.d("Garaje","Click en el boton buscar")
            events()
        }
    }

   private fun events(){
       Log.d("Garaje","Entre a events")
       val bundle: Bundle? = intent.extras
       val folioET: EditText = findViewById(R.id.txtFolio)
       val tecnicoid: Int? = bundle?.getInt("tecnicoId")
       var folioTxt = folioET.text.toString().trim()

       getDetalleTelefono(folioTxt,tecnicoid!!)

   }

    private fun getDetalleTelefono(folio:String,tecnicoid:Int) {

        var call: Call<TelefonData> = SfApi.instance.getDetalleTelefonoByFolio(folio)
        call.enqueue(object : Callback<TelefonData?> {
            override fun onResponse(call: Call<TelefonData?>, response: Response<TelefonData?>) {
                if (response.isSuccessful) {
                    Log.d("Garaje", "RESPONSE SUCCESFULL")
                    var detalleTelefonoData: TelefonData = response.body()!!
                    var detalleActual: com.example.smartFix.apiRetrofit.models.Detalle = detalleTelefonoData.resultados.get(0)
                    val tecid: Int?
                    tecid = detalleActual.tecnicoid
                    Log.d("Garaje", "tec id $tecid")
                    if (tecid == 0) {
                        folioProceso(folio, tecnicoid,2)
                        envioDatos(detalleActual)
                    }else if (tecid != tecnicoid){
                        Toast.makeText(applicationContext,"El teléfono ya se encuentra asignado a otro técnico", Toast.LENGTH_LONG).show()
                        return
                    }
                    Log.d("Garaje", "ENTRE AL GET EXITOSAMENTE ${detalleTelefonoData.resultados}")
                    envioDatos(detalleActual)
                }
            }

            override fun onFailure(call: Call<TelefonData?>, t: Throwable) {
                Log.d("Garaje", "${t.toString()}")
            }
        })
    }

    fun envioDatos(detalle: Detalle){
        val intent = Intent(this@FolioTelefonoActivity,TelefonoFormsActivity::class.java)
        intent.putExtra("marca",detalle.marca)
        intent.putExtra("modelo",detalle.modelo)
        intent.putExtra("descripcion",detalle.descripcion_ingreso)
        intent.putExtra("color",detalle.color)
        intent.putExtra("estatus",detalle.estatusid)
        startActivity(intent)
    }

    private fun folioProceso(folio:String, tecnicoid:Int,estatusid:Int){
        Log.d("Garaje","Entre a folio $tecnicoid $folio")
        val data = PatchData(estatusid,tecnicoid)
        var call: Call<AsignacionTecnicoResponse> = SfApi.instance.asignarTecnico(folio, data)
        call.enqueue(object: Callback<AsignacionTecnicoResponse?>{
            override fun onResponse(call: Call<AsignacionTecnicoResponse?>, response: Response<AsignacionTecnicoResponse?>) {
                if (response.isSuccessful){
                    var dataFolio: AsignacionTecnicoResponse = response.body()!!
                    print(dataFolio.tecnicoid)
                    print(dataFolio.folio)
                    Log.d("Garage","DATO ERROR ${dataFolio.error}")
                }
            }

            override fun onFailure(call: Call<AsignacionTecnicoResponse?>, t: Throwable) {
                Log.d("Garaje","${t.toString()}")
            }


        })
    }




}




