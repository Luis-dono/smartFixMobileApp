package com.example.smartFix.apiRetrofit

import com.example.smartFix.DetalleResponse
import com.example.smartFix.apiRetrofit.models.*
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
            .baseUrl("https://api-smartfixing.auplex.mx")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
            OkHttpClient().newBuilder().build()
            ).build().create(SfApi::class.java)
    }

    @GET("/telefono/detalle/{folio}")
    fun getDetalleTelefonoByFolio(@Path("folio")folio:String?):Call<TelefonData>

    @PATCH("/telefono/{folio}/estatus")
    fun asignarTecnico(@Path("folio")folio:String?, @Body params: PatchData):Call<AsignacionTecnicoResponse>

    @FormUrlEncoded
    @POST("/login")
    fun userLogin(
        @Field("usuario") usuario: String,
        @Field("password") password: String
    ):Call<LoginResponse>

    //b263ae1b
    /*
    * "error": false,
    "folio": "7e1f4bab",
    "tecnicoid": 6
    * */
}