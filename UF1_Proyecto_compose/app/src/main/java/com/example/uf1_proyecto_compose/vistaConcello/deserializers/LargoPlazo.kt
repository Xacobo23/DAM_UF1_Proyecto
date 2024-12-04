package com.example.uf1_proyecto_compose.vistaConcello.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PredLargoPlazo : JsonDeserializer<PredConcelloLargoPrazo> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PredConcelloLargoPrazo {
        val jsonObject = json.asJsonObject
        val predLargoPrazo = jsonObject.get("predMPrazo").asJsonObject
        val arrayPredDiaLargoPrazo = predLargoPrazo.get("listaPredDiaMPrazo").asJsonArray
        val listaPredDiaLargoPrazo: MutableList<PredDiaLargoPrazo> = mutableListOf()

        arrayPredDiaLargoPrazo.forEach {
            listaPredDiaLargoPrazo.add(
                PredDiaLargoPrazo(
                    it.asJsonObject.get("dataPredicion").asString,
                    it.asJsonObject.get("dia").asInt,
                    it.asJsonObject.get("icoCeo1").asInt,
                    it.asJsonObject.get("icoCeo2").asInt,
                    it.asJsonObject.get("icoCeo3").asInt,
                    it.asJsonObject.get("icoVento").asInt,
                    it.asJsonObject.get("probIcoCeo1").asInt,
                    it.asJsonObject.get("probIcoCeo2").asInt,
                    it.asJsonObject.get("probIcoCeo3").asInt,
                    it.asJsonObject.get("tMax").asInt,
                    it.asJsonObject.get("tMin").asInt
                )
            )
        }

        return PredConcelloLargoPrazo(listaPredDiaLargoPrazo)

    }
}

data class PredConcelloLargoPrazo(
    val listaPredLargoPrazo: List<PredDiaLargoPrazo>
)

data class PredDiaLargoPrazo(
    val dataPredicion: String,
    val dia: Int,
    val icoCeo1: Int,
    val icoCeo2: Int,
    val icoCeo3: Int,
    val icoVento: Int,
    val probIcoCeo1: Int,
    val probIcoCeo2: Int,
    val probIcoCeo3: Int,
    val tMax: Int,
    val tMin: Int
)