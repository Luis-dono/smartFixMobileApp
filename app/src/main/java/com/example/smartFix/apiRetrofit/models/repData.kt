package com.example.recyclerviewexample

import com.example.smartFix.recyclermanejoreparaciones.ReparacionPendiente

data class repData(
    val error: Boolean,
    val resultados: List<ReparacionPendiente>,
    val rows: Int
) {
}