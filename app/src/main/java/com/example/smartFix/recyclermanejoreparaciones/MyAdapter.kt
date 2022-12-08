package com.example.smartFix.recyclermanejoreparaciones
import android.content.Context
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.smartFix.R
import com.example.smartFix.apiRetrofit.SfApi
import com.example.smartFix.apiRetrofit.models.CambioStatusReparacionResponse
import com.example.smartFix.apiRetrofit.models.PatchDataReparacion
import com.example.smartFix.activity.manejoreparacionesActivity
import kotlinx.android.synthetic.main.item_reparacion.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyAdapter (context: manejoreparacionesActivity, arraylist:ArrayList<ModelClass>, folio:String) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    private val folio:String
    private val context2:Context
    private lateinit var view:View
    val arrayList: ArrayList<ModelClass>
    val arrayListaux:ArrayList<ModelClass>
    init {
      this.folio=folio
        this.arrayList=arraylist
        this.context2=context
        this.arrayListaux=arraylist
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context)
       view=layoutInflater.inflate(R.layout.item_reparacion,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var modelClass=arrayListaux[position]
        if(modelClass.status.equals("Listo")){
            holder.itemView.checkboxstatusrep.isEnabled=false
        }
        holder.itemView.status.text=modelClass.status
        holder.itemView.Tiporeparacion.text=modelClass.tiporefaccion
        holder.itemView.IDrep.text= modelClass.id.toString()
        holder.itemView.checkboxstatusrep.isChecked=modelClass.isSelected
        holder.itemView.checkboxstatusrep.setOnClickListener {
           if(holder.itemView.checkboxstatusrep.isChecked&&!modelClass.isSelected){
               arrayListaux[position].isSelected=true
               println("id entrante "+arrayListaux.get(position).id)
               actualizarStatusReoaracion(arrayList[position])
               holder.itemView.checkboxstatusrep.isEnabled=false
               holder.itemView.status.text="Listo"
               modelClass.status="Listo"
               arrayListaux[position]=modelClass
           }
       }

    }

    fun getArraylist():ArrayList<ModelClass>{
        return arrayListaux
    }

    override fun getItemCount(): Int {
        return  arrayList.size
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var checkbox:CheckBox=itemView.findViewById(R.id.checkboxstatusrep)
        init{
            itemView.checkboxstatusrep.setOnClickListener { v->
                val ischecked=(v as CheckBox).isChecked
                arrayList[adapterPosition].isSelected=ischecked
                notifyDataSetChanged()
            }
        }
    }
    fun actualizarStatusReoaracion(modelselected:ModelClass){
        val data= PatchDataReparacion(folio,5)
        println(modelselected.id)
        var call: Call<CambioStatusReparacionResponse> = SfApi.instance.actualizarStatusReparacion(modelselected.id,data.folio,data.estatusid)
        call.enqueue(object: Callback<CambioStatusReparacionResponse?> {
            override fun onResponse(
                call: Call<CambioStatusReparacionResponse?>,
                response: Response<CambioStatusReparacionResponse?>
            ) {
                if(response.isSuccessful){
                    var respuesta: CambioStatusReparacionResponse? =response.body()
                    println("resouesta"+ respuesta.toString())
                }
            }

            override fun onFailure(call: Call<CambioStatusReparacionResponse?>, t: Throwable) {
                println("fallo rotundamente")
            }

        })

    }
}



