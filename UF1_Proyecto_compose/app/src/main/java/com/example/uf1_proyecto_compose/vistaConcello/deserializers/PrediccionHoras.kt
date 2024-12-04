package com.example.uf1_proyecto_compose.vistaConcello.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PrediccionHoras : JsonDeserializer<PredHoraria> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PredHoraria {
        val jsonObject = json.asJsonObject
        val jsonPredHoraria = jsonObject.get("predHoraria").asJsonObject
        val arrayListaPredDiaHora = jsonPredHoraria.get("listaPredDiaHoraria").asJsonArray

        val listaDias: MutableList<Dia> = mutableListOf()
        arrayListaPredDiaHora.forEach { dia ->
            val listaPredHora: MutableList<PredHora> = mutableListOf()
            dia.asJsonObject.get("listaPredHora").asJsonArray.forEach {
                listaPredHora.add(
                    PredHora(
                        it.asJsonObject.get("dataPredicion").asString,
                        it.asJsonObject.get("icoCeo").asInt,
                        it.asJsonObject.get("icoVento").asInt,
                        it.asJsonObject.get("tMedia").asInt
                    )
                )
            }
            listaDias.add(
                Dia(
                    dia.asJsonObject.get("dia").asInt,
                    listaPredHora
                )
            )
        }

        return PredHoraria(listaDias)
    }

}

data class PredHoraria(
    val listaPredHoraria: List<Dia>
)

data class Dia(
    val dia: Int,
    val predHoras: List<PredHora>
)

data class PredHora(
    val dataPredicion: String,
    val icoCeo: Int,
    val icoVento: Int,
    val tMedia: Int
)