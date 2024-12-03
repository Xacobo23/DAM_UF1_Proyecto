package com.example.uf1_proyecto_compose.vistaConcello

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class SunDeserializer : JsonDeserializer<Horas>{
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Horas {
        val jsonObject = json.asJsonObject

        val result = jsonObject.get("results").asJsonObject

        return Horas(
            result.get("sunrise").asString,
            result.get("sunset").asString
        )
    }

}

data class Horas(
    val sunrise: String,
    val sunset: String
)

