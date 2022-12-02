package com.example.smartFix.apiRetrofit


import com.example.recyclerviewexample.repData
import com.example.smartFix.apiRetrofit.models.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface SfApi {

    companion object{
        val instance = Retrofit.Builder()
            .baseUrl("https://api-smartfixing.auplex.mx")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder().build()).build().create(SfApi::class.java)
    }

    @GET("/telefono/detalle/{folio}")
    fun getDetalleTelefonoByFolio(@Path("folio")folio:String?):Call<TelefonData>

    @GET("/reparacion/{idrep}")
    fun getReparaciones(@Path("idrep") id:String):Call<repData>

    @PATCH("/telefono/{folio}/estatus")
    fun asignarTecnico(@Path("folio")folio:String?, @Body params: PatchData):Call<AsignacionTecnicoResponse>


    @FormUrlEncoded
    @POST("/detalle")
    fun agregarReparacion(
        @Field("telefono_folio")telefono_folio:String,
        @Field("refaccionid")refaccionid:Int,
        @Field("descripcion_falla")descripcion_falla:String):Call<RepResponse>

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
    // jose@smartfixing.com
    //23a8f4f2
    //59a7aa94
    //e9f29770
    //7bc6e8f7
}