package com.example.smartFix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewexample.RefaccionProvider
import com.example.recyclerviewexample.Reparacion
import com.example.recyclerviewexample.afapyer.Refactionadapter

import com.example.recyclerviewexample.repData
import com.example.smartFix.apiRetrofit.SfApi
import com.example.smartFix.apiRetrofit.models.RepResponse
import com.example.smartFix.apiRetrofit.models.refaccion
import com.example.smartFix.databinding.ActivityAgregarReparacionesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class reparacionesActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var binding: ActivityAgregarReparacionesBinding
    private var listarepraciones:ArrayList<refaccion> =ArrayList()
    private var folio:String="1a5e2688"
    lateinit var btnagregar: Button

    var sublista : ArrayList<String> = ArrayList()
    private var repdispo:ArrayList<Reparacion> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // SuperHeroProvider.SuperHero1
        setContentView(R.layout.activity_agregar_reparaciones)
        binding=ActivityAgregarReparacionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()
        obtenerReparaciones()
        mandarreparaciones()
        btnagregar=findViewById(R.id.Agregar)
        btnagregar.setOnClickListener {
            agregarrep()

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
    private fun mandarreparaciones(){
        var datosaux:RepResponse = RepResponse(folio,1,"pantalla rota")
        var call : Call<RepResponse> = SfApi.instance.agregarReparacion(folio,11,"pila nocarga")
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
        while(contador<sublista.size){
            if(spinner.selectedItem.toString().equals(repdispo.get(contador).tipo)){
                RefaccionProvider.agregarrefaccion(refaccion(repdispo.get(contador).marca,repdispo.get(contador).modelo,repdispo.get(contador).tipo,repdispo.get(contador).precio))
            }
            contador++
        }
        println("reparaciones en lista"+ RefaccionProvider.refaccionlist.toString())
        initRecyclerview()
    }

    override fun onClick(p0: View?) {
        btnagregar.setOnClickListener {
            agregarrep()

        }
    }
}