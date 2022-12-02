package com.example.recyclerviewexample

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
    }

}