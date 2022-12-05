package com.example.smartFix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_manejoreparaciones.*
import com.example.smartFix.apiRetrofit.SfApi
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartFix.apiRetrofit.models.CambioStatusReparacionResponse
import com.example.smartFix.apiRetrofit.models.PatchDataReparacion
import com.example.smartFix.apiRetrofit.models.ReparacionDisponibleData
import com.example.smartFix.recyclermanejoreparaciones.ModelClass
import com.example.smartFix.recyclermanejoreparaciones.MyAdapter
import com.example.smartFix.recyclermanejoreparaciones.ReparacionPendiente


class manejoreparacionesActivity : AppCompatActivity() {
    private var folio:String=""
    private var reparacionesRestantes:ArrayList<ModelClass> =ArrayList()
    private var repracionesaux:ArrayList<ModelClass> = ArrayList()
    private var data : ArrayList<ReparacionPendiente> = ArrayList()
    private lateinit var btnGuardar:Button
    private lateinit var  myAdapteraux:MyAdapter
   // private lateinit var myadapteraux:MyAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manejoreparaciones)
        var bundle: Bundle? =intent.extras
        folio= bundle?.getString("folio").toString()
        println("folio entrante sexo"+folio)
        obtenerreparaciones()
        btnGuardar=findViewById(R.id.guardarreparaciones)
        btnGuardar.setOnClickListener{
                guardarYasignarStatus()
        }
      //  event()
    }

    private fun guardarYasignarStatus() {
        println("data al fuardar "+data.toString())
        var reparacionesterminadas:ArrayList<ModelClass>
        var reparacioneslistas:ArrayList<ModelClass>
        reparacioneslistas=repracionesaux
    }

    private fun event(){

            println("data es "+ data.toString())

            println("datos despues de obtenerlos "+reparacionesRestantes.toString())
            var myAdapter = MyAdapter(this,reparacionesRestantes,folio)

            repracionesaux=myAdapter.arrayListaux
            RecyclerViewReps.layoutManager=LinearLayoutManager(this)
            RecyclerViewReps.itemAnimator=DefaultItemAnimator()
            RecyclerViewReps.addItemDecoration(
                DividerItemDecoration(
                    this,
                    LinearLayoutManager.VERTICAL
                )
            )
            RecyclerViewReps.adapter=myAdapter
    }
    private fun obtenerreparaciones():ArrayList<ReparacionPendiente>{
            println("entro al metodo")
            var call: Call<ReparacionDisponibleData> = SfApi.instance.obtenerRepaConfirmadas(folio)
            var reparcionesActuales:ArrayList<ReparacionPendiente> =ArrayList()

            println("obtener reparaciones entro aca")

            call.enqueue(object: Callback<ReparacionDisponibleData?>{
                override fun onResponse(call: Call<ReparacionDisponibleData?>, response: Response<ReparacionDisponibleData?>) {
                    if(response.isSuccessful){
                        var reparaciones: ReparacionDisponibleData? =response.body()
                        print("reparaciones"+reparaciones.toString())
                        reparcionesActuales= reparaciones?.resultados as ArrayList<ReparacionPendiente>
                        data=reparcionesActuales
                        getData()
                        event()
                        println("data actual- "+reparcionesActuales.toString())
                        println("primer elemento "+reparcionesActuales.get(0).descripcion_falla)
                    }else{
                        println("reesponse gei "+response)
                    }


                }

                override fun onFailure(call: Call<ReparacionDisponibleData?>, t: Throwable) {
                    println(t.toString())
                }

            })
        return reparcionesActuales


    }
    private fun getData(){

        for (i in data){
            var stri:String=i.tipo
            var status:String=i.estatus_reparacion
            var aprove:Int=i.aprobada
            var idrep:Int=i.detalle_reparacionid
            println("tipo actual "+stri)
            println("id reparaciojn "+status)
            println("aprobada "+aprove)

            if(aprove!=1)continue
            this.reparacionesRestantes.add(ModelClass(stri, status,idrep, false))


            println("agregar "+reparacionesRestantes.get(0).tiporefaccion)
        }


    }




    }

