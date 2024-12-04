package com.example.uf1_proyecto_compose

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.vistaConcello.AvisosDeserializer
import com.example.uf1_proyecto_compose.vistaConcello.Horas
import com.example.uf1_proyecto_compose.vistaConcello.ListaPrediccionAvisos
import com.example.uf1_proyecto_compose.vistaConcello.ObservacionActualConcello
import com.example.uf1_proyecto_compose.vistaConcello.ObservacionConcello
import com.example.uf1_proyecto_compose.vistaConcello.PredConcelloCurtoPrazo
import com.example.uf1_proyecto_compose.vistaConcello.PredConcelloLargoPrazo
import com.example.uf1_proyecto_compose.vistaConcello.PredHoraria
import com.example.uf1_proyecto_compose.vistaConcello.PredLargoPlazo
import com.example.uf1_proyecto_compose.vistaConcello.PrediccionAvisos
import com.example.uf1_proyecto_compose.vistaConcello.PrediccionCurtoPrazo
import com.example.uf1_proyecto_compose.vistaConcello.PrediccionHoras
import com.example.uf1_proyecto_compose.vistaConcello.SunDeserializer
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class ViewModelTiempo(context: Context, idConcello: Int) : ViewModel() {



    private val _concelloObservacion =
        mutableStateOf<ObservacionConcello>(ObservacionConcello(-9999, -9999, 0, "", 0.0, 0.0))
    val concelloObservacion = _concelloObservacion

    private val _concelloCurtoPrazo = mutableStateOf<PredConcelloCurtoPrazo>(
        PredConcelloCurtoPrazo(
            listaPredDiaConcello = listOf()
        )
    )
    val concelloCurtoPrazo = _concelloCurtoPrazo

    private val _concelloPredHoras = mutableStateOf<PredHoraria>(
        PredHoraria(
            listaPredHoraria = listOf()
        )
    )
    val concelloPredHoras = _concelloPredHoras

    private val _concelloLargoPrazo = mutableStateOf<PredConcelloLargoPrazo>(
        PredConcelloLargoPrazo(
            listaPredLargoPrazo = listOf()
        )
    )
    val concelloLargoPrazo = _concelloLargoPrazo

    private val _hSunriseSunset = mutableStateOf(Horas("1:00:01 AM", "1:00:01 PM"))
    val hSunriseSunset = _hSunriseSunset

    private val _concelloAvisos = mutableStateOf<ListaPrediccionAvisos>(
        ListaPrediccionAvisos(listOf())
    )
    val concelloAvisos = _concelloAvisos


    init {

        viewModelScope.launch {
            val deferredConcelloObservacion = async(Dispatchers.IO) {
                try {
                    val jsonStringConcelloObservacion = fetchUrlContent(
                        context.resources.getString(R.string.observacionConcello).plus(idConcello)
                    )
                    val gsonConcelloObservacion = GsonBuilder()
                        .registerTypeAdapter(
                            ObservacionConcello::class.java,
                            ObservacionActualConcello()
                        )
                        .create()
                    gsonConcelloObservacion.fromJson(
                        jsonStringConcelloObservacion,
                        ObservacionConcello::class.java
                    )
                } catch (e: Exception) {
                    ObservacionConcello(-1, -1, 0, "Error", 0.0, 0.0)
                }
            }

            val deferredConcelloCurtoPrazo = async(Dispatchers.IO) {
                try {
                    val jsonStringConcelloCurtoPrazo = fetchUrlContent(
                        context.resources.getString(R.string.predCortoConcello).plus(idConcello)
                            .plus(context.resources.getString(R.string.request_locale))
                    )
                    val gsonConcelloCurtoPrazo = GsonBuilder()
                        .registerTypeAdapter(
                            PredConcelloCurtoPrazo::class.java,
                            PrediccionCurtoPrazo()
                        )
                        .create()
                    gsonConcelloCurtoPrazo.fromJson(
                        jsonStringConcelloCurtoPrazo,
                        PredConcelloCurtoPrazo::class.java
                    )
                } catch (e: Exception) {
                    PredConcelloCurtoPrazo(listOf())
                }
            }

            val deferredConcelloPredHoras = async(Dispatchers.IO) {
                try {
                    val jsonStringPredHoraria = fetchUrlContent(
                        context.resources.getString(R.string.predHorariaConcello).plus(idConcello)
                            .plus(context.resources.getString(R.string.request_locale))
                    )
                    val gsonConcelloPredHoraria = GsonBuilder()
                        .registerTypeAdapter(PredHoraria::class.java, PrediccionHoras())
                        .create()
                    gsonConcelloPredHoraria.fromJson(jsonStringPredHoraria, PredHoraria::class.java)
                } catch (e: Exception) {
                    PredHoraria(listOf())
                }
            }

            val deferredConcelloPredLargoPrazo = async(Dispatchers.IO) {
                try {
                    val jsonStringPredLargoPrazo = fetchUrlContent(
                        context.resources.getString(R.string.predLargoConcello).plus(idConcello)
                            .plus(context.resources.getString(R.string.predLargoConcelloDia))
                            .plus(context.resources.getString(R.string.request_locale))
                    )
                    val gsonConcelloPredLargoPrazo = GsonBuilder()
                        .registerTypeAdapter(PredConcelloLargoPrazo::class.java, PredLargoPlazo())
                        .create()
                    gsonConcelloPredLargoPrazo.fromJson(
                        jsonStringPredLargoPrazo,
                        PredConcelloLargoPrazo::class.java
                    )
                } catch (e: Exception) {
                    PredLargoPlazo()
                }
            }

            val deferredSunriseSunset = async(Dispatchers.IO) {
                try {
                    val sunSetRise =
                        fetchUrlContent(context.resources.getString(R.string.sunRiseSet))
                    val gsonSunSetRise = GsonBuilder()
                        .registerTypeAdapter(Horas::class.java, SunDeserializer())
                        .create()
                    gsonSunSetRise.fromJson(sunSetRise, Horas::class.java)
                } catch (e: Exception) {
                    Horas("1:00:01 AM", "1:00:01 PM")
                }
            }

            val deferredConcelloAvisos = async(Dispatchers.IO) {
                try {
                    val avisosConcello = fetchUrlContent("https://servizos.meteogalicia.gal/mgrss/predicion/adversos/jsonAvisosConcellos.action?idConcello=36013&dia=-1")
//                        """
//{
//  "listaDiaConcellos": [
//    {
//      "dia": 0,
//      "listaAvisosConcellos": [
//        {
//          "idconcello": 15010,
//          "idNivel": 1,
//          "nomeConcello": "A Coruña",
//          "dataAviso": "2024-12-02T08:00:00",
//          "dataIni": "2024-12-02T10:00:00",
//          "dataFin": "2024-12-02T20:00:00",
//          "idTipoAlerta": 3,
//          "tipoalerta_gl": "Alerta laranxa por chuvias intensas",
//          "tipoalerta_es": "Alerta naranja por lluvias intensas"
//        },
//        {
//          "idconcello": 15030,
//          "idNivel": 2,
//          "nomeConcello": "Lugo",
//          "dataAviso": "2024-12-02T08:00:00",
//          "dataIni": "2024-12-02T12:00:00",
//          "dataFin": "2024-12-02T18:00:00",
//          "idTipoAlerta": 2,
//          "tipoalerta_gl": "Alerta amarela por ventos fortes",
//          "tipoalerta_es": "Alerta amarilla por vientos fuertes"
//        },
//        {
//          "idconcello": 15030,
//          "idNivel": 3,
//          "nomeConcello": "Lugo",
//          "dataAviso": "2024-12-02T08:00:00",
//          "dataIni": "2024-12-02T12:00:00",
//          "dataFin": "2024-12-02T18:00:00",
//          "idTipoAlerta": 2,
//          "tipoalerta_gl": "Alerta amarela por ventos fortes",
//          "tipoalerta_es": "Alerta amarilla por vientos fuertes"
//        }
//      ]
//    },
//    {
//      "dia": 1,
//      "listaAvisosConcellos": []
//    },
//    {
//      "dia": 2,
//      "listaAvisosConcellos": []
//    }
//  ]
//}
//                """

                    val gsonAvisosConcello = GsonBuilder()
                        .registerTypeAdapter(
                            ListaPrediccionAvisos::class.java,
                            AvisosDeserializer()
                        )
                        .create()
                    println(
                        gsonAvisosConcello.fromJson(
                            avisosConcello,
                            ListaPrediccionAvisos::class.java
                        )
                    )
                    gsonAvisosConcello.fromJson(avisosConcello, ListaPrediccionAvisos::class.java)

                } catch (e: Exception) {
                    PrediccionAvisos(0, listOf())
                }
            }

            val results = awaitAll(
                deferredConcelloObservacion,
                deferredConcelloCurtoPrazo,
                deferredConcelloPredHoras,
                deferredConcelloPredLargoPrazo,
                deferredSunriseSunset,
                deferredConcelloAvisos
            )

            _concelloObservacion.value = results[0] as ObservacionConcello
            _concelloCurtoPrazo.value = results[1] as PredConcelloCurtoPrazo
            _concelloPredHoras.value = results[2] as PredHoraria
            _concelloLargoPrazo.value = results[3] as PredConcelloLargoPrazo
            _hSunriseSunset.value = results[4] as Horas
            _concelloAvisos.value = results[5] as ListaPrediccionAvisos
        }
    }

    private suspend fun fetchUrlContent(url: String): String {
        return withContext(Dispatchers.IO) {
            try {
                // Abrir conexión HTTP
                val urlConnection = URL(url).openConnection() as HttpURLConnection
                // Leer la respuesta de la URL
                urlConnection.inputStream.bufferedReader().use { it.readText() }
            } catch (e: Exception) {
                // Si ocurre un error, devolver el mensaje de error
                "Error: ${e.localizedMessage}"
            }
        }
    }

}