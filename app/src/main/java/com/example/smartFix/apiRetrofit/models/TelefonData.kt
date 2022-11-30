package com.example.smartFix.apiRetrofit.models

data class TelefonData(
    val error: Boolean,
    val resultados: List<Detalle>,
    val rows: Int
)