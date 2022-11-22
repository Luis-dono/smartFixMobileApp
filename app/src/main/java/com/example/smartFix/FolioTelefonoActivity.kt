package com.example.smartFix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class FolioTelefonoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_folio_telefono)
    }

    fun onClick(view: View) {
        val intent = Intent(this,TelefonoFormsActivity::class.java)
        startActivity(intent)
    }
}