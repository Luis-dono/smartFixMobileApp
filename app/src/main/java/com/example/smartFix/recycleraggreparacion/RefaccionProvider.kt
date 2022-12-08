
package com.example.smartFix.recycleraggreparacion

import com.example.smartFix.apiRetrofit.models.refaccion

class RefaccionProvider {

    companion object{
        var refaccionlist: ArrayList<refaccion> = java.util.ArrayList()
        fun getrefaccionList():ArrayList<refaccion>{
            return refaccionlist
        }
        fun agregarrefaccion(refaccion: refaccion){
            refaccionlist.add(refaccion)
        }

        fun limpiarRefacciones(){
            refaccionlist.clear()
        }
    }

}