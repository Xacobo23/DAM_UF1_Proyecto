package com.example.uf1_proyecto_compose.vistaConcello

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

fun listaConcello(json : String): List<Concello>{
    val gson = GsonBuilder()
        .registerTypeAdapter(Concello::class.java, ConcelloDeserializer())
        .create()

    val type = object : TypeToken<List<Concello>>() {}.type


    val listaConcellos: List<Concello> = gson.fromJson(
                json, type)

    return listaConcellos
}

class ConcelloDeserializer : JsonDeserializer<Concello> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Concello {
        val jsonObject = json.asJsonObject
        val id = jsonObject.get("id").asInt
        val name = jsonObject.get("concello").asString

        return Concello(id, name)
    }
}

data class Concello(
    val id: Int,
    val name: String
)



