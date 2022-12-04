package com.example.recyclerviewexample

data class repData(
    val error: Boolean,
    val resultados: List<Reparacion>,
    val rows: Int
) {
}