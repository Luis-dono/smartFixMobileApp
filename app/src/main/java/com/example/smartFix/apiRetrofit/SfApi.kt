package com.example.smartFix.apiRetrofit

import com.example.smartFix.models.Resultado
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface SfApi {

    companion object{
        val instance = Retrofit.Builder().baseUrl("https://api-smartfixing.auplex.mx/").addConverterFactory(GsonConverterFactory.create()).client(
            OkHttpClient().newBuilder().build()
        ).build().create(SfApi::class.java)
    }

@GET("telefono/detalle/{folio}")
fun getDetalle(@Path("folio") folio:String): Call<Resultado>

}