package com.example.uf1_proyecto_compose.vistaConcello.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ObservacionActualConcello : JsonDeserializer<ObservacionConcello> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ObservacionConcello {
        val jsonObject = json.asJsonObject
        val listaJsonArray = jsonObject.getAsJsonArray("listaObservacionConcellos")

        val observacionObject = listaJsonArray[0].asJsonObject

        // Parse individual fields
        return ObservacionConcello(
            icoEstadoCeo = observacionObject.get("icoEstadoCeo").asInt,
            icoVento = observacionObject.get("icoVento").asInt,
            idConcello = observacionObject.get("idConcello").asInt,
            nomeConcello = observacionObject.get("nomeConcello").asString,
            sensacionTermica = observacionObject.get("sensacionTermica").asDouble,
            temperatura = observacionObject.get("temperatura").asDouble
        )
    }
}

data class ObservacionConcello(
    val icoEstadoCeo: Int,
    val icoVento: Int,
    val idConcello: Int,
    val nomeConcello: String,
    val sensacionTermica: Double,
    val temperatura: Double
)