package com.example.smartFix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private val detalle= mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telefono_forms)

    }

    override fun onClick(p0: View?) {
      when(p0?.id){
          R.id.botonsillo -> {
              val query2 : String? =getIntent().getStringExtra("folio")
              println("el texto de l caja $query2")
              if (query2 != null) {
                  searchByFolio(query2)

              }
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
                    val detalleretro=detallesRetro?.resultados ?: emptyList()
                    this@TelefonoFormsActivity.detalle.clear()
                    this@TelefonoFormsActivity.detalle.addAll(detalleretro)
                    print(this@TelefonoFormsActivity.detalle)
                    Log.d("etiqueta de ejecucion", "no mames si llego aqui")

                }else{
                    println("valio verga")
                }

            }
        }
    }
     private fun addInfoForms(){
         val editText=findViewById<EditText>(R.id.editTextmarca)
        editText.setText(detalle[1])
     }
}
//1c798653