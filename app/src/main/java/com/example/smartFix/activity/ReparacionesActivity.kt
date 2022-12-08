package com.example.smartFix.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartFix.R
import com.example.smartFix.recycleraggreparacion.Refactionadapter
import com.example.smartFix.apiRetrofit.SfApi
import com.example.smartFix.apiRetrofit.models.*
import com.example.smartFix.databinding.ActivityAgregarReparacionesBinding
import com.example.smartFix.recycleraggreparacion.RefaccionProvider
import com.example.smartFix.recycleraggreparacion.Reparacion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class reparacionesActivity : AppCompatActivity()   {
    private lateinit var binding: ActivityAgregarReparacionesBinding
    private var listarepraciones:ArrayList<refaccion> =ArrayList()
    private var folio:String=""
    lateinit var btnagregar: Button
    lateinit var btnguardar:Button
    var estatusid: Int = 0
    var reparacionesFlagna :Boolean=false
    var sublista : ArrayList<String> = ArrayList()
    private var repdispo:ArrayList<Reparacion> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        RefaccionProvider.limpiarRefacciones()
        val modeloid:Int? = bundle?.getInt("modeloid")
        initRecyclerview()
        obtenerReparaciones(modeloid!!)
        btnguardar=findViewById(R.id.guardar)
        btnagregar=findViewById(R.id.Agregar)
        btnagregar.setOnClickListener {
            agregarrep()
        }
        btnguardar.setOnClickListener {
            cambiarstatus(bundle)
            val intent = Intent()
            intent?.putExtra("estatusid",estatusid)
            setResult(RESULT_OK,intent)
            Toast.makeText(applicationContext,"Reparaciones agregadas", Toast.LENGTH_LONG).show()
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun initRecyclerview(){

        binding.recyclerRefacciones.layoutManager=LinearLayoutManager(this)
        binding.recyclerRefacciones.adapter= Refactionadapter(RefaccionProvider.refaccionlist)
    }

    private fun llenarSpinne(listareps:ArrayList<Reparacion>){
        var spinner:Spinner=findViewById(R.id.SPINNER_REPS)
        sublista = fillArray(listareps)
        var adapter :ArrayAdapter<String>  =  ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sublista)
        spinner.adapter = adapter
        println("lista reps"+sublista.toString())
    }

    private fun obtenerReparaciones(modeloid:Int) {
        var call: Call<repData> = SfApi.instance.getReparaciones("$modeloid")
        var reparacioneslist: ArrayList<Reparacion> = ArrayList()
        call.enqueue(object: Callback<repData?>{
            override fun onResponse(call: Call<repData?>, response: Response<repData?>) {
                if(response.isSuccessful){
                    var reparaciones: repData = response.body()!!
                    reparacioneslist = reparaciones.resultados as ArrayList<Reparacion>
                    repdispo=reparacioneslist
                    llenarSpinne(reparacioneslist)
                }
            }

            override fun onFailure(call: Call<repData?>, t: Throwable) {
                Log.d("Garaje","${t.toString()}")
            }

        })

    }

    private fun fillArray(lista:ArrayList<Reparacion>): ArrayList<String> {
        var listilla:ArrayList<String> = ArrayList()
        var aux : ArrayList<Reparacion> = lista
        var contador:Int= aux.size

        for (i in aux){
            listilla.add(i.tipo)
        }
        return listilla
    }

    private fun mandarreparaciones(refaccionid:Int,descripcion:String,contador:Int){
        var call : Call<RepResponse> = SfApi.instance.agregarReparacion(folio,refaccionid,descripcion)
        call.enqueue(object : Callback<RepResponse> {
            override fun onResponse(call: Call<RepResponse>, response: Response<RepResponse>) {
                if(response.isSuccessful){
                    println(response.body().toString())
                    RefaccionProvider.agregarrefaccion(refaccion(repdispo.get(contador).marca,
                        repdispo.get(contador).modelo,repdispo.get(contador).tipo,repdispo.get(contador).precio))
                    initRecyclerview()
                    Log.d("Garage","Reparacion agregada")
                }else{
                    Log.d("Garage","ESTA REPARACION YA EXISTE ")
                    Toast.makeText(applicationContext,"Esta reparacion ya existe", Toast.LENGTH_LONG).show()
                }

            }
            override fun onFailure(call: Call<RepResponse>, t: Throwable) {
                Log.d("Garage","Falla al querer mandar la reparacion")
            }

        } )


    }

    private fun agregarrep(){
        var spinner:Spinner=findViewById(R.id.SPINNER_REPS)
        var contador:Int=0
        var indexadd:Int=0
        while(contador<sublista.size){
            if(spinner.selectedItem.toString().equals(repdispo.get(contador).tipo)){
                indexadd=contador
                break
            }
            contador++
        }
        println("refaccion id  "+repdispo.get(indexadd))
        mandarreparaciones(repdispo.get(indexadd).refaccionid,"falla -"+spinner.selectedItem.toString(),contador)
        println("indice igual a= "+indexadd)
        initRecyclerview()
    }

    private fun cambiarstatus(bundle:Bundle){
        var status:Int=7
        if(!reparacionesFlagna){
            status=3
        }
        estatusid = status
        val tecnicoid: Int? = bundle?.getInt("tecnicoid")
        Log.d("Garage","TECNICO ID: $tecnicoid STATUS: $status FOLIO: $folio")
        val data= PatchData(status,tecnicoid)
        val cal: Call<AsignacionTecnicoResponse> =SfApi.instance.asignarTecnico(folio,data)
        cal.enqueue(object : Callback<AsignacionTecnicoResponse> {
            override fun onResponse(
                call: Call<AsignacionTecnicoResponse>,
                response: Response<AsignacionTecnicoResponse>
            ) {
                if(response.isSuccessful){
                    Log.d("Garage","SUCCESFULL 3 ")
                    var dataresp: AsignacionTecnicoResponse =response.body()!!
                }else{
                    Log.d("Garage","FAILURE SUCCESFULL 3 $response")
                }
            }

            override fun onFailure(call: Call<AsignacionTecnicoResponse>, t: Throwable) {
                Log.d("Garage","FAILURE CAMBIAR ESTATUS 3 ${t.toString()}")
            }
        })
    }

}