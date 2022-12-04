package com.example.smartFix.recyclermanejoreparaciones

data class ReparacionPendiente (
    val detalle_reparacionid: Int,
    val folio:String,
    val refaccionid:Int,
    val tipo:String,
    val descripcion_falla:String,
    val estatus_reparacion:String,
    val aprobada:Int
    ){
}