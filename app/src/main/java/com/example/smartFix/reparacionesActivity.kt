package com.example.smartFix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewexample.RefaccionProvider
import com.example.recyclerviewexample.Reparacion
import com.example.recyclerviewexample.afapyer.Refactionadapter

import com.example.recyclerviewexample.repData
import com.example.smartFix.apiRetrofit.SfApi
import com.example.smartFix.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class reparacionesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // SuperHeroProvider.SuperHero1
        setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()
        obtenerReparaciones()
    }

    fun initRecyclerview(){

        binding.recyclerRefacciones.layoutManager=LinearLayoutManager(this)
        binding.recyclerRefacciones.adapter=Refactionadapter(RefaccionProvider.refaccionlist)
    }
    fun llenarSpinne(listareps:ArrayList<Reparacion>){
        var spinner:Spinner=findViewById(R.id.SPINNER_REPS)
        val sublista : ArrayList<String> = fillArray(listareps)
        var adapter : ArrayAdapter<String> =  ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sublista)
        spinner.adapter = adapter
        println("lista reps"+sublista.toString())
    }
    fun obtenerReparaciones(): ArrayList<Reparacion> {
        var call: Call<repData> = SfApi.instance.getReparaciones("1")
        var reparacioneslist: ArrayList<Reparacion> = ArrayList()
        call.enqueue(object: Callback<repData?>{
            override fun onResponse(call: Call<repData?>, response: Response<repData?>) {
                if(response.isSuccessful){
                    var reparaciones: repData = response.body()!!
                    reparacioneslist = reparaciones.resultados as ArrayList<Reparacion>
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
    fun fillArray(lista:ArrayList<Reparacion>): ArrayList<String> {
        var listilla:ArrayList<String> = ArrayList()
        println("lista entrante "+lista.toString())
        var aux : ArrayList<Reparacion> = lista
        var contador:Int= aux.size
        println("objetos igual a "+contador)
        for (i in aux){
            println("si entra el for"+i)
            listilla.add(i.tipo)
        }

        return listilla
    }
}