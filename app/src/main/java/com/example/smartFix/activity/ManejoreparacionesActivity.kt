package com.example.smartFix.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_manejoreparaciones.*
import com.example.smartFix.apiRetrofit.SfApi
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartFix.R
import com.example.smartFix.apiRetrofit.models.*
import com.example.smartFix.recyclermanejoreparaciones.ModelClass
import com.example.smartFix.recyclermanejoreparaciones.MyAdapter
import com.example.smartFix.recyclermanejoreparaciones.ReparacionPendiente


class manejoreparacionesActivity : AppCompatActivity() {
    private var folio:String=""
    private var reparacionesRestantes:ArrayList<ModelClass> =ArrayList()
    private var repracionesaux:ArrayList<ModelClass> = ArrayList()
    private var data : ArrayList<ReparacionPendiente> = ArrayList()
    private lateinit var btnGuardar:Button
    private lateinit var  myAdapter:MyAdapter
    private var flagReparacionesListas:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manejoreparaciones)
        var bundle: Bundle? =intent.extras
        folio= bundle?.getString("folio").toString()
        val tecnicoid: Int? = bundle?.getInt("tecnicoid")
        obtenerreparaciones()
        btnGuardar=findViewById(R.id.guardarreparaciones)
        btnGuardar.setOnClickListener{
            verificarReparacionesListas()
            if(flagReparacionesListas){
                cambiarEstatus(folio,tecnicoid!!,5)
                val intent = Intent()
                intent?.putExtra("estatusid",5)
                Toast.makeText(applicationContext,"Reparaciones finalizadas", Toast.LENGTH_LONG).show()
                setResult(RESULT_OK,intent)
            }else{
                Log.d("Garage","LAS REPARACIONES NO HAN FINALIZADO FLAG: $flagReparacionesListas")
                Toast.makeText(applicationContext,"Reparaciones no finalizadas por completo", Toast.LENGTH_LONG).show()

            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun event(){

            println("data es "+ data.toString())

            println("datos despues de obtenerlos "+reparacionesRestantes.toString())
            myAdapter = MyAdapter(this,reparacionesRestantes,folio)

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
    private fun obtenerreparaciones(){
            var call: Call<ReparacionDisponibleData> = SfApi.instance.obtenerRepaConfirmadas(folio)
            var reparcionesActuales:ArrayList<ReparacionPendiente> =ArrayList()
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
                        print(response.message())
                    }


                }

                override fun onFailure(call: Call<ReparacionDisponibleData?>, t: Throwable) {
                    println(t.toString())
                }

            })
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
            this.reparacionesRestantes.add(ModelClass(stri, status,idrep, aprove,false))

            println("agregar "+reparacionesRestantes.get(0).tiporefaccion)
        }

    }

    private fun verificarReparacionesListas(){
        val data2 = myAdapter.getArraylist()
        var contRepAprobado:Int = 0
        var contRepListas:Int = 0

        for (i in data2){
            var status:String=i.status
            var aprove:Int=i.aprobado
            if(aprove!=1)continue
            contRepAprobado++
            if (status.equals("Listo"))contRepListas++
            Log.d("Garage","status: $status aprobado: $aprove")
        }
        if (contRepAprobado == contRepListas){
            flagReparacionesListas=true;
        }
        Log.d("Garage","REAPARACIONES APROBADAS $contRepAprobado | REPARACIONES LISTAS $contRepListas FLAG: $flagReparacionesListas")

    }

    private fun cambiarEstatus(folio:String, tecnicoid:Int,estatusid:Int){
        val data = PatchData(estatusid,tecnicoid)
        var call: Call<AsignacionTecnicoResponse> = SfApi.instance.asignarTecnico(folio, data)
        call.enqueue(object: Callback<AsignacionTecnicoResponse?>{
            override fun onResponse(call: Call<AsignacionTecnicoResponse?>, response: Response<AsignacionTecnicoResponse?>) {
                if (response.isSuccessful){
                    var dataFolio: AsignacionTecnicoResponse = response.body()!!
                    print(dataFolio.tecnicoid)
                    // print(dAataFolio.folio)
                    Log.d("Garage","DATO ERROR ${dataFolio.error}")
                }
            }

            override fun onFailure(call: Call<AsignacionTecnicoResponse?>, t: Throwable) {
                Log.d("Garaje","${t.toString()}")
            }


        })
    }




    }
