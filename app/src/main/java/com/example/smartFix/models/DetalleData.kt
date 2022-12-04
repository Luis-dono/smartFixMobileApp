package com.example.smartFix.models

import com.google.gson.annotations.SerializedName

class DetalleData (@SerializedName("error")var error:Boolean=false,
                   @SerializedName("resultados") var resultados:List<Resultado>,
                       )
