package com.example.smartFix.recyclermanejoreparaciones

class ModelClass(var tiporefaccion:String, var status:String,var id:Int, var isSelected: Boolean) {
    override fun toString(): String {
        return "ModelClass(tiporefaccion='$tiporefaccion', status='$status', id=$id, isSelected=$isSelected)"
    }
}