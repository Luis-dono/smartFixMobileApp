package com.example.smartFix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.smartFix.apiRetrofit.SfApi
import com.example.smartFix.apiRetrofit.models.AsignacionTecnicoResponse
import com.example.smartFix.apiRetrofit.models.LoginResponse
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

       folioProceso(folioTxt,tecnicoid!!)

       }

    private fun folioProceso(folio:String, tecnicoid:Int){
        Log.d("Garaje","Entre a folio $tecnicoid $folio")
        val data = PatchData(2,tecnicoid)
        var call: Call<AsignacionTecnicoResponse> = SfApi.instance.asignarTecnico(folio, data)
        call.enqueue(object: Callback<AsignacionTecnicoResponse?>{
            override fun onResponse(call: Call<AsignacionTecnicoResponse?>, response: Response<AsignacionTecnicoResponse?>) {
                if (response.isSuccessful){
                    var dataFolio: AsignacionTecnicoResponse = response.body()!!
                    print(dataFolio.tecnicoid)
                    print(dataFolio.folio)
                    Log.d("Garage","DATO ERROR ${dataFolio.error}")
                    if (!dataFolio.error!!){
                        getDetalleTelefono(folio)
                    }
                }
            }

            override fun onFailure(call: Call<AsignacionTecnicoResponse?>, t: Throwable) {
                Log.d("Garaje","${t.toString()}")
            }


        })
    }

    private fun getDetalleTelefono(folio:String){
        var call: Call<TelefonData> = SfApi.instance.getDetalleTelefonoByFolio(folio)
        call.enqueue(object: Callback<TelefonData?>{
            override fun onResponse(call: Call<TelefonData?>, response: Response<TelefonData?>) {
                if (response.isSuccessful){
                    var detalleTelefonoData: TelefonData = response.body()!!
                    Log.d("Garaje","ENTRE AL GET EXITOSAMENTE ${detalleTelefonoData.resultados}")
                }
            }

            override fun onFailure(call: Call<TelefonData?>, t: Throwable) {
                Log.d("Garaje","${t.toString()}")
            }


        })
    }

}




