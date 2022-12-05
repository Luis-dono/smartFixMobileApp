package com.example.smartFix.apiRetrofit.models

data class CambioStatusReparacionResponse(
    val error:Boolean,
    val id:String,
    val telefono_folio:String,
    val rowsAffected:Int

) {
}