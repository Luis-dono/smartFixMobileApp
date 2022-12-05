package com.example.smartFix.recycleraggreparacion

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartFix.R
import com.example.smartFix.apiRetrofit.models.refaccion
import com.example.smartFix.databinding.ItemRefaccionesBinding

class RefaccionViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val binding= ItemRefaccionesBinding.bind(view)
    val marca=view.findViewById<TextView>(R.id.textvMarca)
    val modelo=view.findViewById<TextView>(R.id.textvModelo)
    val refaccion=view.findViewById<TextView>(R.id.NombreRefacc)
    val precio=view.findViewById<TextView>(R.id.precio_real)

    fun render(refaccionModel: refaccion){
        marca.text=refaccionModel.marca
        modelo.text=refaccionModel.modelo
        refaccion.text=refaccionModel.refaccion
    }
}