package com.example.smartFix

data class Detalle (    var folio: String,//pieza
                        val lote: String,//marca
                        val estatusid: Int,
                        var nombre :String,
                        var apellido: String,
                        var tecnico: Int?,
                        var marcaid: Int,
                        var marca: String,
                        var modelo: String,
                        var color: String,
                        var descripcion_ingreso: String,
                        var creado_en: String,
                        var precio_final: String){


}