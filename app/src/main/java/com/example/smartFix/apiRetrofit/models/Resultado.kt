package com.example.smartFix.apiRetrofit.models

data class Resultado(
    val apellido: String,
    val creado_en: String,
    val estatus: String,
    val estatusid: Int,
    val folio: String,
    val lote: String,
    val nombre: String,
    val rolid: Int,
    val tecnico: Any,
    val tecnicoid: Any,
    val usuarioid: Int
)