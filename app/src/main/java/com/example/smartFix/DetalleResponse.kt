package com.example.smartFix

import com.google.gson.annotations.SerializedName

class DetalleResponse (@SerializedName("error")var error:Boolean=false,
                       @SerializedName("resultados") var resultados:List<String>)
