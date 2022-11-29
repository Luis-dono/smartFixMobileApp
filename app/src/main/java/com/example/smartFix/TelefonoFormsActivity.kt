package com.example.smartFix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import com.example.smartFix.apiRetrofit.SfApi
import com.example.smartFix.models.Resultado
import kotlinx.coroutines.*
import retrofit2.awaitResponse

class TelefonoFormsActivity : AppCompatActivity(), OnClickListener {
    private val detalle= mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telefono_forms)
        searchByFolio()
    }

    override fun onClick(p0: View?) {

    }


    private fun searchByFolio() {
        val apiinstance=SfApi.instance
        GlobalScope.launch (Dispatchers.IO){
            val response=apiinstance.getDetalle().awaitResponse()
            if(response.isSuccessful){
                val data: Resultado? =response.body()
                withContext(Dispatchers.Main){
                    val editText=findViewById<EditText>(R.id.editTextmarca)

                    if (data != null) {
                        editText.setText(data.marca)
                        println("el modelo del telefono es--"+data.modelo)
                    }
                }
            }else{
                println("no jala esta mrda")
            }
        }
    }
     private fun addInfoForms(){
         val editText=findViewById<EditText>(R.id.editTextmarca)
        editText.setText(detalle[1])
     }
}
//1c798653