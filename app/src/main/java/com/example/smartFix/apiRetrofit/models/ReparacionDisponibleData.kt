package com.example.smartFix.apiRetrofit.models

import com.example.smartFix.recyclermanejoreparaciones.ReparacionPendiente


class ReparacionDisponibleData(
    val error: Boolean,
    var folio:String,
    val resultados: List<ReparacionPendiente>,
    val rows: Int
) {
}