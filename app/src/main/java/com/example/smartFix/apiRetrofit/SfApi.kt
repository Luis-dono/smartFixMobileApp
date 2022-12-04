package com.example.smartFix.apiRetrofit


import com.example.recyclerviewexample.AsignacionStatusResponse
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

    @FormUrlEncoded
    @PATCH("/telefono/{folio}/estatus")
    fun asignarStatus(@Path("folio")folio:String?, @Field("estatusid")statusid:Int?,@Field("tecnicoid")tecnicoid:Int?):Call<AsignacionStatusResponse>

    @GET("/telefono/detalle/{folio}")
    fun getDetalleTelefonoByFolio(@Path("folio")folio:String?):Call<TelefonData>

    @GET("/reparacion/{idrep}")
    fun getReparaciones(@Path("idrep") id:String):Call<repData>

    @PATCH("/telefono/{folio}/estatus")
    fun asignarTecnico(@Path("folio")folio:String?, @Body params: PatchData):Call<AsignacionTecnicoResponse>

     @GET("/detalle/reparaciones/{folio}?rolid=2")
         fun obtenerRepaConfirmadas(@Path("folio")folio:String?):Call<ReparacionDisponibleData>
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