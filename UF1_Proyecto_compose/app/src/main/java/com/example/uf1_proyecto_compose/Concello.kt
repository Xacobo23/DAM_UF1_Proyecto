package com.example.uf1_proyecto_compose

import com.google.gson.GsonBuilder

fun main(){
    val gson = GsonBuilder()
}

data class Provincia(
    val nombre: String,
    val concellos: List<Concello>
)

data class Concello(
    val Identificador: Int,
    val NomeDoConcellos: String
)



