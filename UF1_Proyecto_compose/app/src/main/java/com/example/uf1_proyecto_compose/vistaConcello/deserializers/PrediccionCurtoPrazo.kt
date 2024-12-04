package com.example.uf1_proyecto_compose.vistaConcello.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import java.lang.reflect.Type
import java.time.LocalDateTime

class PrediccionCurtoPrazo : JsonDeserializer<PredConcelloCurtoPrazo> {
    override fun deserialize(
        jsonElement: JsonElement,
        p1: Type?,
        p2: JsonDeserializationContext?
    ): PredConcelloCurtoPrazo {
        val jsonObject = jsonElement.asJsonObject
        val predConcello = jsonObject.get("predConcello").asJsonObject

        val listaPred = predConcello.get("listaPredDiaConcello").asJsonArray

        val listaPredDiaConcello = mutableListOf<PredDiaConcello>()


        listaPred.forEach { pred ->
            val predDia = pred.asJsonObject

            val nivelAviso: Int = if (predDia.get("nivelAviso") == JsonNull.INSTANCE) 0
            else predDia.get("nivelAviso").asInt


            listaPredDiaConcello.add(
                PredDiaConcello(
                    Ceo(
                        predDia.get("ceo").asJsonObject.get("manha").asInt,
                        predDia.get("ceo").asJsonObject.get("tarde").asInt,
                        predDia.get("ceo").asJsonObject.get("noite").asInt
                    ),
                    LocalDateTime.parse(predDia.get("dataPredicion").asString),
                    nivelAviso,
                    Pchoiva(
                        predDia.get("pchoiva").asJsonObject.get("manha").asInt,
                        predDia.get("pchoiva").asJsonObject.get("tarde").asInt,
                        predDia.get("pchoiva").asJsonObject.get("noite").asInt
                    ),
                    predDia.get("tMax").asInt,
                    predDia.get("tMin").asInt,
                    TemperaturaFranxa(
                        predDia.get("tmaxFranxa").asJsonObject.get("manha").asInt,
                        predDia.get("tmaxFranxa").asJsonObject.get("tarde").asInt,
                        predDia.get("tmaxFranxa").asJsonObject.get("noite").asInt
                    ),
                    TemperaturaFranxa(
                        predDia.get("tminFranxa").asJsonObject.get("manha").asInt,
                        predDia.get("tminFranxa").asJsonObject.get("tarde").asInt,
                        predDia.get("tminFranxa").asJsonObject.get("noite").asInt
                    ),
                    predDia.get("uvMax").asInt,
                    Vento(
                        predDia.get("vento").asJsonObject.get("manha").asInt,
                        predDia.get("vento").asJsonObject.get("tarde").asInt,
                        predDia.get("vento").asJsonObject.get("noite").asInt
                    )
                )
            )
        }
        return PredConcelloCurtoPrazo(listaPredDiaConcello)
    }
}

data class PredConcelloCurtoPrazo(
    val listaPredDiaConcello: List<PredDiaConcello>,
)

data class PredDiaConcello(
    val ceo: Ceo,
    val dataPredicion: LocalDateTime,
    val nivelAviso: Int, // Puede ser null
    val pchoiva: Pchoiva,
    val tMax: Int,
    val tMin: Int,
    val tmaxFranxa: TemperaturaFranxa,
    val tminFranxa: TemperaturaFranxa,
    val uvMax: Int,
    val vento: Vento
)

data class Ceo(
    val manha: Int,
    val tarde: Int,
    val noite: Int
)

data class Pchoiva(
    val manha: Int,
    val tarde: Int,
    val noite: Int
)

data class TemperaturaFranxa(
    val manha: Int,
    val noite: Int,
    val tarde: Int
)

data class Vento(
    val manha: Int,
    val tarde: Int,
    val noite: Int
)