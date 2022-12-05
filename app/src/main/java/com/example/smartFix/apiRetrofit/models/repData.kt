package com.example.recyclerviewexample

import com.example.smartFix.apiRetrofit.models.Reparacion

data class repData(
    val error: Boolean,
    val resultados: List<Reparacion>,
    val rows: Int
) {
}