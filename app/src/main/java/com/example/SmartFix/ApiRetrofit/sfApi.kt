package com.example.SmartFix.ApiRetrofit

import com.example.SmartFix.DetalleResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface sfApi {

    companion object{
        val instance = Retrofit.Builder().baseUrl("http://localhost:5010/").addConverterFactory(GsonConverterFactory.create()).client(
            OkHttpClient().newBuilder().build()
        ).build().create(sfApi::class.java)
    }
    @GET
    fun getTelefonoByFolio(@Url url:String):Response<DetalleResponse>
}