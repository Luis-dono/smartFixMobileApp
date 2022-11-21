package com.example.SmartFix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.SmartFix.ApiRetrofit.sfApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class folioTelefono_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_folio_telefono)
        searchByFolio("1c798653")
    }

    fun onClick(view: View) {

    }

   private fun getValor(view: View){
       val editText= findViewById<EditText>(R.id.editTextTextPersonName)
        val folio=editText.text

    }
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api-smartfixing.auplex.mx/detalle/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByFolio(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call=getRetrofit().create(sfApi::class.java).getTelefonoByFolio(query)
            val detalles=call.body()
            if(call.isSuccessful){
                println(detalles)

            }else{
                //show error
            }
        }
    }
}