package com.example.smartFix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import android.widget.Toast
import com.example.smartFix.apiRetrofit.SfApi
import com.example.smartFix.models.DetalleResponse
import com.example.smartFix.models.Resultado
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class TelefonoFormsActivity : AppCompatActivity(), OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telefono_forms)
        var folio: String? = intent.extras?.getString("folio")
        if (folio != null) {
            merequetengue(folio)
        };
    }
    private fun merequetengue(folio : String){
       println(folio);
        var call:Call<DetalleResponse> =SfApi.instance.getDetalle(folio+"/")
        call.enqueue(object : Callback<DetalleResponse>{
            override fun onResponse(call: Call<DetalleResponse>, response:Response<DetalleResponse>){
                if(response.isSuccessful){
                    var dataDetalle: DetalleResponse = response.body()!!
                    println("marca telefono")
                    println(dataDetalle.toString())
                }
            }

            override fun onFailure(call: Call<DetalleResponse>, t: Throwable) {
                print("pincheperraputamadre fallo completamente")

            }
        })
    }
    override fun onClick(p0: View?) {

    }



}
//1c798653
//e798e01f