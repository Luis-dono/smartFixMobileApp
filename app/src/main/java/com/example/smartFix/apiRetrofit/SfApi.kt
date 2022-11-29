package com.example.smartFix.apiRetrofit

import com.example.smartFix.models.DetalleResponse
import com.example.smartFix.models.Resultado
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface SfApi {

    companion object{
        val instance = Retrofit.Builder().baseUrl("https://api-smartfixing.auplex.mx/telefono/detalle/").addConverterFactory(GsonConverterFactory.create()).client(
            OkHttpClient().newBuilder().build()
        ).build().create(SfApi::class.java)
    }

@GET
fun getDetalle(@Url url:String): Call<DetalleResponse>

}