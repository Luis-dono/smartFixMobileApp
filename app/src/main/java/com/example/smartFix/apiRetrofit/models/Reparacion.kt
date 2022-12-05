package com.example.smartFix.apiRetrofit.models

data class Reparacion (
    val refaccionid: Int,
    val precio: String,
    val tipo_reparacionesid:Int,
    val tipo:String,
    val marcaid:Int,
    val marca:String,
    val modeloid:Int,
    val modelo:String
){
}