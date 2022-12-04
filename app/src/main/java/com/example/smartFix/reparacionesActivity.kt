package com.example.smartFix

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewexample.*
import com.example.recyclerviewexample.afapyer.Refactionadapter

import com.example.smartFix.apiRetrofit.SfApi
import com.example.smartFix.apiRetrofit.models.RepResponse
import com.example.smartFix.apiRetrofit.models.refaccion
import com.example.smartFix.databinding.ActivityAgregarReparacionesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class reparacionesActivity : AppCompatActivity()   {
    private lateinit var binding: ActivityAgregarReparacionesBinding
    private var listarepraciones:ArrayList<refaccion> =ArrayList()
    private var folio:String=""
    lateinit var btnagregar: Button
    lateinit var btnguardar:Button

    var reparacionesFlagna :Boolean=false
    var sublista : ArrayList<String> = ArrayList()
    private var repdispo:ArrayList<Reparacion> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // SuperHeroProvider.SuperHero1
        setContentView(R.layout.activity_agregar_reparaciones)
        var bundle: Bundle  = intent.extras!!
        binding=ActivityAgregarReparacionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var check=findViewById<CheckBox>(R.id.checkboxded)
        check.setOnCheckedChangeListener { compoundButton, b ->
            if(check.isChecked){
                reparacionesFlagna=true
            }
        }
        folio= bundle?.getString("folio")!!

        intent.putExtra("folio",folio)
        initRecyclerview()
        obtenerReparaciones()
        btnguardar=findViewById(R.id.guardar)
        btnagregar=findViewById(R.id.Agregar)
        btnagregar.setOnClickListener {
            agregarrep()
        }
        btnguardar.setOnClickListener {
            cambiarstatus(bundle)
        }


    }

    private fun initRecyclerview(){

        binding.recyclerRefacciones.layoutManager=LinearLayoutManager(this)
        binding.recyclerRefacciones.adapter=Refactionadapter(RefaccionProvider.refaccionlist)
    }
    private fun llenarSpinne(listareps:ArrayList<Reparacion>){
        var spinner:Spinner=findViewById(R.id.SPINNER_REPS)
        sublista = fillArray(listareps)
        var adapter :ArrayAdapter<String>  =  ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sublista)
        spinner.adapter = adapter
        println("lista reps"+sublista.toString())
    }
    private fun obtenerReparaciones(): ArrayList<Reparacion> {
        var call: Call<repData> = SfApi.instance.getReparaciones("1")
        var reparacioneslist: ArrayList<Reparacion> = ArrayList()
        call.enqueue(object: Callback<repData?>{
            override fun onResponse(call: Call<repData?>, response: Response<repData?>) {
                if(response.isSuccessful){
                    var reparaciones: repData = response.body()!!
                    reparacioneslist = reparaciones.resultados as ArrayList<Reparacion>
                    repdispo=reparacioneslist
                    println("la puta que te pario"+reparacioneslist.toString())
                    llenarSpinne(reparacioneslist)
                }
            }

            override fun onFailure(call: Call<repData?>, t: Throwable) {
                Log.d("Garaje","${t.toString()}")
            }

        })
        return reparacioneslist
    }
    private fun fillArray(lista:ArrayList<Reparacion>): ArrayList<String> {
        var listilla:ArrayList<String> = ArrayList()
        var aux : ArrayList<Reparacion> = lista
        var contador:Int= aux.size

        for (i in aux){
            println("si entra el for"+i)
            listilla.add(i.tipo)
        }

        return listilla
    }
    private fun mandarreparaciones(refaccionid:Int,descripcion:String){

        // var datosaux:RepResponse = RepResponse(folio,1,"pantalla rota")
        var call : Call<RepResponse> = SfApi.instance.agregarReparacion(folio,refaccionid,descripcion)
        call.enqueue(object : Callback<RepResponse> {
            override fun onResponse(call: Call<RepResponse>, response: Response<RepResponse>) {
                println("no mames si jalo estya madre")
                println(response.body().toString())
            }

            override fun onFailure(call: Call<RepResponse>, t: Throwable) {
                println("valio verga" +
                        "")
            }

        } )


    }
    private fun agregarrep(){
        var spinner:Spinner=findViewById(R.id.SPINNER_REPS)
        var contador:Int=0
        var indexadd:Int=0
        while(contador<sublista.size){
            if(spinner.selectedItem.toString().equals(repdispo.get(contador).tipo)){
                RefaccionProvider.agregarrefaccion(refaccion(repdispo.get(contador).marca,
                    repdispo.get(contador).modelo,repdispo.get(contador).tipo,repdispo.get(contador).precio))
                indexadd=contador
                break
            }
            contador++
        }
        println("refaccion id  "+repdispo.get(indexadd))
        mandarreparaciones(repdispo.get(indexadd).refaccionid,"falla prueba -"+indexadd)
        println("indice igual a= "+indexadd)
        //println("reparaciones en lista"+ RefaccionProvider.refaccionlist.get(indexadd))
        initRecyclerview()
    }
    private fun cambiarstatus(bundle:Bundle){

            var status:Int=7
        println("estado de mierdad---" +reparacionesFlagna )
        if(!reparacionesFlagna){
            status=3
        }
        println("estado de mierdad---"+status +reparacionesFlagna )
        val tecnicoid: Int? = bundle?.getInt("tecnicoid")
        val data= Patchstatus(status,tecnicoid)
        val cal:Call<AsignacionStatusResponse> =SfApi.instance.asignarStatus(folio,data.estatusid,data.tecnicoid)
        cal.enqueue(object : Callback<AsignacionStatusResponse> {
            override fun onResponse(
                call: Call<AsignacionStatusResponse>,
                response: Response<AsignacionStatusResponse>
            ) {
                if(response.isSuccessful){
                    var dataresp: AsignacionStatusResponse =response.body()!!
                    println("si jalo,  "+ (dataresp?.statusid))
                    if (!dataresp.error!!){

                    }


                }

            }

            override fun onFailure(call: Call<AsignacionStatusResponse>, t: Throwable) {
                println("no jalo")
            }


        })
    }

    //1a5e2688
}