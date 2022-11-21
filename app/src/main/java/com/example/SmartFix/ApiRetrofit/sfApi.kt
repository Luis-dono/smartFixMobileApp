package com.example.SmartFix.ApiRetrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface sfApi {
    companion object{
        val instance = Retrofit.Builder().baseUrl("https://api-smartfixing.auplex.mx/").addConverterFactory(GsonConverterFactory.create()).client(
            OkHttpClient().newBuilder().build()
        ).build().create(sfApi::class.java)
    }

    //
}