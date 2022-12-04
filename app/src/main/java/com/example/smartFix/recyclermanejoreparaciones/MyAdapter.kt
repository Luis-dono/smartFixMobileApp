package com.example.smartFix.recyclermanejoreparaciones
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.smartFix.R
import com.example.smartFix.manejoreparacionesActivity
import kotlinx.android.synthetic.main.item_reparacion.view.*
class MyAdapter (context: manejoreparacionesActivity, arraylist:ArrayList<ModelClass>) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    private val context2:Context
     val arrayList: ArrayList<ModelClass>
    val arrayListaux:ArrayList<ModelClass>
    init {
        this.arrayList=arraylist
        this.context2=context
        this.arrayListaux=arrayList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context)
        val view=layoutInflater.inflate(R.layout.item_reparacion,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelClass=arrayListaux[position]
        holder.itemView.Tiporeparacion.text=modelClass.tiporefaccion
        holder.itemView.checkboxstatusrep.isChecked=modelClass.isSelected
        holder.itemView.checkboxstatusrep.setOnClickListener {
            if (holder.itemView.checkboxstatusrep.isChecked){
                arrayListaux.removeAt(position)
                arrayList[position].isSelected=true
            }

        }
        //set event

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

                for(i in arrayList.indices){
                    Log.d("TAG",arrayList.toString())
                }
            }
        }
    }
}



