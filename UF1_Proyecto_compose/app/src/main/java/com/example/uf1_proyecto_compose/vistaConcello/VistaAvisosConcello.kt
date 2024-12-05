package com.example.uf1_proyecto_compose.vistaConcello

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.ViewModelTiempo
import com.example.uf1_proyecto_compose.funciones.colorAviso
import com.example.uf1_proyecto_compose.funciones.descripcionAlerta
import com.example.uf1_proyecto_compose.funciones.monthName
import com.example.uf1_proyecto_compose.funciones.weekDay
import com.example.uf1_proyecto_compose.ui.theme.Gray
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaAvisosConcello(viewModel: ViewModelTiempo) {
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    PullToRefreshBox(
        state = rememberPullToRefreshState(),
        isRefreshing = isRefreshing,
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                isRefreshing = false
            }
        }
    ) {
        val prediccionAvisos = viewModel.concelloAvisos.value.listaPrediccionAvisos
        if (prediccionAvisos.isNotEmpty()) {
            Column {

//                Row(modifier = Modifier.fillMaxWidth().height(16.dp).background(Color(0xFF8891BD))){}
                LazyColumn(
                    modifier = Modifier
                        .background(Color(0xFF8891BD))
                        .padding(top = 30.dp, bottom = 48.dp)
                        .fillMaxSize(),

                    verticalArrangement = Arrangement.Top,

                    ) {
                    prediccionAvisos.forEach {
                        item {

                            if (it.listaAvisosConcellos.isEmpty()) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFF8891BD))
                                        .padding(vertical = 16.dp),
                                    color = Color.White,
                                    text = LocalDate.now()
                                        .plusDays(it.dia.toLong()).dayOfMonth.toString()
                                        .plus(stringResource(id = R.string.deSpace))
                                        .plus(monthName()),
                                    textAlign = TextAlign.Center
                                )
                                Row(
                                    modifier = Modifier.background(Color.White).padding(60.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        text = stringResource(R.string.noAdvice),
                                        textAlign = TextAlign.Center,
                                        color = Color.Black
                                    )
                                }
                            } else {
                                val maxLevel =
                                    it.listaAvisosConcellos.maxOfOrNull { l -> l.idNivel }
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFF8891BD))
                                        .padding(vertical = 16.dp),
                                    color = Color.White,
                                    text = LocalDate.now()
                                        .plusDays(it.dia.toLong()).dayOfMonth.toString()
                                        .plus(stringResource(id = R.string.deSpace))
                                        .plus(monthName()),
                                    textAlign = TextAlign.Center
                                )
                                it.listaAvisosConcellos.forEach { dia ->

                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(colorAviso(maxLevel ?: 0))
                                            .padding(4.dp)
                                            .border(6.dp, colorAviso(dia.idNivel))
                                            .background(color = Color.White)
                                            .padding(22.dp)


                                    ) {

                                        val fechaIni = LocalDateTime.parse(dia.dataIni)
                                        val fechaFin = LocalDateTime.parse(dia.dataFin)

                                        Text(
                                            color = Color.Black,
                                            text = stringResource(R.string.since).plus(
                                                weekDay(
                                                    fechaIni.dayOfWeek.value
                                                ).lowercase()
                                            )
                                                .plus(stringResource(R.string.whiteSpace))
                                                .plus(fechaIni.dayOfMonth)
                                                .plus(stringResource(R.string.from))
                                                .plus(fechaIni.hour)
                                                .plus(stringResource(R.string.dDot00))
                                                .plus(stringResource(R.string.until))
                                                .plus(weekDay(fechaFin.dayOfWeek.value).lowercase())
                                                .plus(stringResource(R.string.whiteSpace))
                                                .plus(fechaFin.dayOfMonth)
                                                .plus(stringResource(R.string.from))
                                                .plus(fechaFin.hour)
                                                .plus(stringResource(R.string.dDot00)),
                                            fontSize = 15.sp
                                        )
                                        HorizontalDivider(
                                            thickness = 8.dp,
                                            color = Color.Transparent
                                        )
                                        Row(
                                            modifier = Modifier.padding(vertical = 8.dp),
                                            verticalAlignment = Alignment.Bottom,

                                            ) {
                                            Text(
                                                color = Color.Black,
                                                text = descripcionAlerta(dia.idTipoAlerta),
                                                fontSize = 19.sp
                                            )
                                        }

                                        if (Locale.current.language == "gl") {
                                            Text(
                                                color = Color.Black,
                                                text = dia.tipoalertaGl
                                            )
                                        } else {
                                            Text(
                                                color = Color.Black,
                                                text = dia.tipoalertaEs
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Row(modifier = Modifier.fillMaxWidth().height(48.dp).background(Color(0xFF8891BD))){}
            }
        }
    }
}


