package com.example.smartFix.apiRetrofit

import com.example.smartFix.DetalleResponse
import com.example.smartFix.apiRetrofit.models.DataLogin
import com.example.smartFix.apiRetrofit.models.LoginResponse
import com.example.smartFix.apiRetrofit.models.Resultado
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface SfApi {

    companion object{
        val instance = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5010")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
            OkHttpClient().newBuilder().build()
            ).build().create(SfApi::class.java)
    }

    @GET
    fun getTelefonoByFolio(@Url url:String):Response<DetalleResponse>

    @GET("/telefono")
    fun getTelefonos(): List<Resultado>

    @FormUrlEncoded
    @POST("/login")
    fun userLogin(
        @Field("usuario") usuario: String,
        @Field("password") password: String
    ):Call<LoginResponse>

}