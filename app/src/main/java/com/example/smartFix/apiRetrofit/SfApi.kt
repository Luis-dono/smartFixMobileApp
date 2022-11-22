package com.example.smartFix.apiRetrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface SfApi {
    companion object{
        val instance = Retrofit.Builder().baseUrl("https://api-smartfixing.auplex.mx/").addConverterFactory(GsonConverterFactory.create()).client(
            OkHttpClient().newBuilder().build()
        ).build().create(SfApi::class.java)
    }
}