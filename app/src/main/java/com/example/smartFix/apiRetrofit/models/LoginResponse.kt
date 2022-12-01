package com.example.smartFix.apiRetrofit.models

data class LoginResponse(
        val error:Boolean,
        val message:String,
        val id:Int,
        val rolid:Int,
        val correo:String,
        val nombre:String,
        val apellido:String
    )

