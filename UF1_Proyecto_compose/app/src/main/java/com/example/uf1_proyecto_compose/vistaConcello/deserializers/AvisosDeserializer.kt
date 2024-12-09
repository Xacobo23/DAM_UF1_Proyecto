package com.example.uf1_proyecto_compose.vistaConcello.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class AvisosDeserializer : JsonDeserializer<ListaPrediccionAvisos> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ListaPrediccionAvisos {
        val jsonObject = json.asJsonObject

        val arrayDias = jsonObject.get("listaDiaConcellos").asJsonArray
        val listaDias: MutableList<PrediccionAvisos> = mutableListOf()

        arrayDias.forEach { item ->
            val listaAvisosConcello: MutableList<AvisoConcellos> = mutableListOf()
            val arrayAvisosConcello = item.asJsonObject.get("listaAvisosConcellos").asJsonArray

            arrayAvisosConcello.forEach {
                listaAvisosConcello.add(
                    AvisoConcellos(
                        it.asJsonObject.get("idConcello").asInt,
                        it.asJsonObject.get("idNivel").asInt,
                        it.asJsonObject.get("nomeConcello").asString,
                        it.asJsonObject.get("dataAviso").asString,
                        it.asJsonObject.get("dataIni").asString,
                        it.asJsonObject.get("dataFin").asString,
                        it.asJsonObject.get("idTipoAlerta").asInt,
                        it.asJsonObject.get("tipoalerta_gl").asString,
                        it.asJsonObject.get("tipoalerta_es").asString,
                    )
                )
            }
            listaDias.add(
                PrediccionAvisos(
                    item.asJsonObject.get("dia").asInt,
                    listaAvisosConcello
                )
            )
        }

        return ListaPrediccionAvisos(listaDias)
    }
}

data class ListaPrediccionAvisos(
    val listaPrediccionAvisos: List<PrediccionAvisos>
)


data class PrediccionAvisos(
    val dia: Int,
    val listaAvisosConcellos: List<AvisoConcellos>
)

data class AvisoConcellos(
    val idconcello: Int,
    val idNivel: Int,
    val nomeConcello: String,
    val dataAviso: String,
    val dataIni: String,
    val dataFin: String,
    val idTipoAlerta: Int,
    val tipoalertaGl: String,
    val tipoalertaEs: String
)