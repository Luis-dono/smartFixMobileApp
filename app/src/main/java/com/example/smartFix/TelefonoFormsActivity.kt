package com.example.smartFix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import android.widget.Toast
import com.example.smartFix.apiRetrofit.SfApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TelefonoFormsActivity : AppCompatActivity(), OnClickListener {
    private val reparaciones= mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telefono_forms)
       
    }

    override fun onClick(p0: View?) {
      when(p0?.id){
          R.id.botonsillo -> {
              val cajatexto=findViewById<EditText>(R.id.editTextTextPersonName)
              val query2 : String=cajatexto.text.toString()
              searchByFolio(query2)
          }
          R.id.botonsillo -> Toast.makeText(this, "botonazo", Toast.LENGTH_LONG).show()
      }
    }
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api-smartfixing.auplex.mx/detalle/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByFolio(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call=getRetrofit().create(SfApi::class.java).getTelefonoByFolio(query)
            val detallesRetro=call.body()
            runOnUiThread(){
                if(call.isSuccessful){
                    println(detallesRetro)
                    val detalle=detallesRetro?.resultados ?: emptyList()
                    reparaciones.clear()
                    reparaciones.addAll(detalle)
                    println("alaverga si jalo esta madre")

                }else{
                    println("valio verga"
                    )
                }

            }
        }
    }

}