package com.example.smartFix.apiRetrofit.models

import com.google.gson.annotations.SerializedName

data class DataLogin(
    @SerializedName("usuario") val email: String,
    @SerializedName("password") val password: String
    )
