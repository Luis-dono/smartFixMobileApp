package com.example.smartFix.apiRetrofit.models

import com.example.smartFix.recycleraggreparacion.Reparacion


data class repData(
    val error: Boolean,
    val resultados: List<Reparacion>,
    val rows: Int
) {
}