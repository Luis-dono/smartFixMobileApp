package com.example.smartFix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.smartFix.apiRetrofit.SfApi
import com.example.smartFix.apiRetrofit.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        events()
    }

    private fun events(){
        val correo : EditText = findViewById(R.id.emailTextField)
        val contraseña : EditText = findViewById(R.id.passwordTextField)
        val loginButton :Button = findViewById(R.id.btnLogin)

        loginButton.setOnClickListener{
            var correoTxt = correo.text.toString().trim()
            var contraseñaTxt = contraseña.text.toString().trim()

            if (correoTxt.isEmpty()){
                correo.error="Correo requerido"
                correo.requestFocus()
                return@setOnClickListener
            }
            if (contraseñaTxt.isEmpty()){
                contraseña.error="Contraseña requerido"
                contraseña.requestFocus()
                return@setOnClickListener
            }
            loginProceso(correoTxt,contraseñaTxt)
        }
    }



    fun loginProceso(correo:String, password:String ){
        var call: Call<LoginResponse> = SfApi.instance.userLogin(correo,password)

        call.enqueue(object: Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    var datosLogin: LoginResponse = response.body()!!
                    print("TECNICO ID ")
                    print(datosLogin.rolid)
                    print(datosLogin.id)
                    if(datosLogin.rolid != 2){
                        Toast.makeText(applicationContext,"Ese correo no es de tecnico",Toast.LENGTH_LONG).show()
                        return
                    }
                    val intent = Intent(this@LoginActivity,FolioTelefonoActivity::class.java)
                    intent.putExtra("tecnicoId",datosLogin.id)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                print(call.toString())
                Toast.makeText(applicationContext,"Contraseña o correo Incorrecto",Toast.LENGTH_LONG).
                show()
            }

        })


       /*SfApi.instance.userLogin(correo,password).enqueue(object: Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                println("ENTRE PUTAS SI")
                print(response)
                println("ENTRE PUTAS")
                if (response.isSuccessful){
                    val intent = Intent(this@LoginActivity,FolioTelefonoActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                val mensaje = t.message
                println(mensaje)
                Toast.makeText(applicationContext,"Contraseña o correo Incorrecto: $mensaje",Toast.LENGTH_LONG).show()
            }

        } )*/
    }

}