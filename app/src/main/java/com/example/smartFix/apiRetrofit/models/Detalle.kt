package com.example.smartFix.apiRetrofit.models

data class Detalle(
    val folio: String,
    val lote: String,
    val estatusid: Int,
    val nombre: String,
    val apellido: String,
    val tecnico: String,
    val tecnicoid: Int,
    val marcaid: Int,
    val marca: String,
    val modeloid: Int,
    val modelo: String,
    val color: String,
    val descripcion_ingreso: String,
    val creado_en: String,
    val precio_final: Double

)