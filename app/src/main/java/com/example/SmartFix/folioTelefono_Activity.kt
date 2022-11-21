package com.example.SmartFix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class folioTelefono_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_folio_telefono)
    }

    fun onClick(view: View) {
        val intent = Intent(this,telefonoForms_Activity::class.java)
        startActivity(intent)
    }
}