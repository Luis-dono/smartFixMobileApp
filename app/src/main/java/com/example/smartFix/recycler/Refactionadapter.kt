package com.example.recyclerviewexample.afapyer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.smartFix.R
import com.example.smartFix.apiRetrofit.models.refaccion

class Refactionadapter(private val RefaccionesList:List<refaccion>) : RecyclerView.Adapter<RefaccionViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RefaccionViewHolder {
        val layoutInFlater=LayoutInflater.from(parent.context)
        return RefaccionViewHolder(layoutInFlater.inflate(R.layout.item_refacciones, parent, false))

    }

    override fun onBindViewHolder(holder: RefaccionViewHolder, position: Int) {
        val item=RefaccionesList[position]
        holder.render(item)

    }

    override fun getItemCount(): Int=RefaccionesList.size


}