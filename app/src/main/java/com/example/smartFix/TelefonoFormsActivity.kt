package com.example.smartFix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.SmartFix.R
import com.example.smartFix.apiRetrofit.sfApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TelefonoFormsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_folio_telefono)

    }

    fun onClick(view: View) {

    }

   private fun getValor(view: View){
       val editText= findViewById<EditText>(R.id.editTextTextPersonName)
        val folio=editText.text

    }



}
