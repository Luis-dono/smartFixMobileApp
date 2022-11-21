package com.example.SmartFix

import com.google.gson.annotations.SerializedName

class DetalleResponse (@SerializedName("error")var error:Boolean=false,
                       @SerializedName("resultados") var resultados:List<Detalle>)
