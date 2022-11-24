package com.example.smartFix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText




class FolioTelefonoActivity : AppCompatActivity() {
    var folio:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_folio_telefono)

    }

    fun onClick(view: View) {
        val intent = Intent(this,TelefonoFormsActivity::class.java)
        getValor()
        intent.putExtra("folio",folio)
        startActivity(intent)
        //1c798653
    }

   private fun getValor(){
       val editText= findViewById<EditText>(R.id.editTextTextPersonName)
        folio=editText.text.toString()

    }



}
