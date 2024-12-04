package com.example.uf1_proyecto_compose.vistaConcello


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.ViewModelTiempo
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import com.example.uf1_proyecto_compose.funciones.PutImage
import com.example.uf1_proyecto_compose.funciones.backgroundBrush
import com.example.uf1_proyecto_compose.funciones.colorAviso
import com.example.uf1_proyecto_compose.funciones.iconSkyState
import com.example.uf1_proyecto_compose.funciones.iconWindState
import com.example.uf1_proyecto_compose.funciones.monthName
import com.example.uf1_proyecto_compose.funciones.weekDay
import com.example.uf1_proyecto_compose.ui.theme.Gray
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaPrediccionConcello(viewModel: ViewModelTiempo) {

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


        val concelloObservacion = viewModel.concelloObservacion.value

        val listaPredDiaConcello = viewModel.concelloCurtoPrazo.value.listaPredDiaConcello



        Box {
            val insets = WindowInsets.systemBars
            val density = LocalDensity.current
            Column(
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
                    .background(backgroundBrush(viewModel.hSunriseSunset.value))
                    .padding(
                        top = with(density) { insets.getTop(density).toDp() },
                        bottom = with(density) { insets.getBottom(density).toDp() }
                    )
//                    .background(Color.Cyan).fillMaxSize()


            ) {

                Text(
                    text = concelloObservacion.nomeConcello,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    fontSize = 30.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = weekDay().plus(stringResource(id = R.string.comma))
                        .plus(LocalDate.now().dayOfMonth)
                        .plus(stringResource(id = R.string.deSpace)).plus(monthName()),
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp

                )

                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PutImage(iconSkyState(concelloObservacion.icoEstadoCeo), Modifier.size(250.dp))

                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                    ) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = viewModel.concelloObservacion.value.temperatura.toString()
                                    .plus(stringResource(id = R.string.deg)),
                                fontSize = 40.sp,
                                color = Color.White,
                                modifier = Modifier
                            )

                            Text(
                                text = viewModel.concelloObservacion.value.sensacionTermica.toString()
                                    .plus(stringResource(id = R.string.deg)),
                                fontSize = 15.sp,
                                color = Color.White,
                                modifier = Modifier
                            )
                        }

                        PutImage(
                            iconWindState(concelloObservacion.icoVento),
                            Modifier
                                .size(100.dp)
                                .padding(start = 8.dp)
                        )

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                PutImage(R.drawable.max, Modifier.size(50.dp))
                                if (listaPredDiaConcello.isNotEmpty())
                                    Text(
                                        text = listaPredDiaConcello[0].tMax.toString()
                                            .plus(stringResource(id = R.string.deg)),
                                        color = Color.White,
                                        fontSize = 20.sp
                                    )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                PutImage(R.drawable.min, Modifier.size(50.dp))
                                if (listaPredDiaConcello.isNotEmpty())
                                    Text(
                                        text = listaPredDiaConcello[0].tMin.toString()
                                            .plus(stringResource(id = R.string.deg)),
                                        color = Color.White,
                                        fontSize = 20.sp
                                    )
                            }


                        }
                    }
                }

                //Prediccion Horaria

                LazyRow(
                    modifier = Modifier.background(Color.Gray.copy(alpha = 0.5f))
                ) {
                    val listaPredHoraria = viewModel.concelloPredHoras.value.listaPredHoraria
                    if (listaPredHoraria.isNotEmpty()) {
                        listaPredHoraria.forEach { dia ->
                            item {
                                Column {
                                    Text(
                                        text = weekDay(
                                            LocalDate.now()
                                                .plusDays(dia.dia.toLong()).dayOfWeek.value
                                        ),
                                        color = Color.White,
                                        modifier = Modifier.padding(horizontal = 10.dp),

                                        )
                                    Row {
                                        dia.predHoras.forEach {
                                            if (LocalDateTime.parse(it.dataPredicion) > LocalDateTime.now()
                                                    .minusHours(1)
                                            ) {
                                                Column(
                                                    modifier = Modifier
                                                        .padding(horizontal = 16.dp)
                                                        .align(Alignment.CenterVertically),
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                ) {
                                                    Text(
                                                        text = LocalDateTime.parse(it.dataPredicion).hour.toString()
                                                            .plus(stringResource(id = R.string.dDot00)),
                                                        color = Color.White,
                                                    )
                                                    PutImage(
                                                        iconSkyState(it.icoCeo),
                                                        Modifier.size(70.dp)
                                                    )
                                                    Row(
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        PutImage(
                                                            iconWindState(it.icoVento),
                                                            Modifier.size(50.dp)
                                                        )
                                                        Text(
                                                            text = it.tMedia.toString()
                                                                .plus(stringResource(id = R.string.deg)),
                                                            color = Color.White,
                                                        )
                                                    }
                                                }
                                                VerticalDivider(
                                                    modifier = Modifier
                                                        .width(1.dp)
                                                        .height(130.dp),
//                                                        .padding(horizontal = 8.dp),
                                                    color = Color.LightGray
                                                )
                                            }


                                        }
                                    }
                                }
                                VerticalDivider(modifier = Modifier.padding(horizontal = 6.dp))
                            }
                        }
                    }
                }

                //Proximos dias

                Row(
                    modifier = Modifier
                        .background(color = Gray.copy(alpha = 0.7f))
                        .fillMaxWidth()

                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp),
                        text = stringResource(id = R.string.nextDays),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }

                Column {
                    for (i in 1 until listaPredDiaConcello.size) {
                        if (listaPredDiaConcello.isNotEmpty()) {
                            Row {
                                Column {
                                    Row(
                                        modifier = Modifier
                                            .padding(bottom = 16.dp)
                                            .background(Color.Gray.copy(alpha = 0.3f))
                                            .fillMaxWidth()
                                            .padding(end = 16.dp, start = 16.dp),

                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = weekDay(listaPredDiaConcello[i].dataPredicion.dayOfWeek.value)
                                                .plus(stringResource(id = R.string.comma))
                                                .plus(listaPredDiaConcello[i].dataPredicion.dayOfMonth)
                                                .plus(stringResource(id = R.string.deSpace))
                                                .plus(monthName(listaPredDiaConcello[i].dataPredicion.month.value)),
                                            color = Color.White
                                        )
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            PutImage(R.drawable.max, Modifier.size(32.dp))
                                            Text(
                                                text = listaPredDiaConcello[i].tMax.toString()
                                                    .plus(stringResource(id = R.string.deg)),
                                                color = Color.White,
                                            )
                                            PutImage(R.drawable.min, Modifier.size(32.dp))
                                            Text(
                                                text = listaPredDiaConcello[i].tMin.toString()
                                                    .plus(stringResource(id = R.string.deg)),
                                                color = Color.White,
                                            )

                                        }
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceAround
                                    ) {
                                        Column {
                                            Text(
                                                text = stringResource(id = R.string.morning),
                                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                                textAlign = TextAlign.Center,
                                                color = Color.White,
                                            )
                                            PutImage(
                                                iconSkyState(listaPredDiaConcello[i].ceo.manha),
                                                Modifier
                                                    .size(70.dp)
                                                    .align(Alignment.CenterHorizontally),
                                            )
                                            Row(
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                PutImage(
                                                    iconWindState(listaPredDiaConcello[i].vento.manha),
                                                    Modifier.size(35.dp)
                                                )

                                                Row(
                                                    modifier = Modifier,
                                                    verticalAlignment = Alignment.CenterVertically,


                                                    ) {
                                                    PutImage(
                                                        R.drawable.raindrop_measure,
                                                        Modifier.size(30.dp)
                                                    )
                                                    Text(
                                                        color = Color.White,
                                                        text = if (listaPredDiaConcello[i].pchoiva.manha.toString() == "-9999") {
                                                            stringResource(R.string.dash)
                                                        } else listaPredDiaConcello[i].pchoiva.manha.toString()
                                                            .plus(stringResource(id = R.string.porcentage))
                                                    )
                                                }
                                            }
                                        }
                                        Column {
                                            Text(
                                                text = stringResource(id = R.string.afternoon),
                                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                                textAlign = TextAlign.Center,
                                                color = Color.White,

                                                )
                                            PutImage(
                                                iconSkyState(listaPredDiaConcello[i].ceo.tarde),
                                                Modifier
                                                    .size(70.dp)
                                                    .align(Alignment.CenterHorizontally),
                                            )
                                            Row(
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                PutImage(
                                                    iconWindState(listaPredDiaConcello[i].vento.tarde),
                                                    Modifier.size(35.dp)
                                                )

                                                Row(
                                                    modifier = Modifier,
                                                    verticalAlignment = Alignment.CenterVertically,


                                                    ) {
                                                    PutImage(
                                                        R.drawable.raindrop_measure,
                                                        Modifier.size(30.dp)
                                                    )
                                                    Text(
                                                        color = Color.White,
                                                        text = if (listaPredDiaConcello[i].pchoiva.tarde.toString() == "-9999") {
                                                            stringResource(R.string.dash)
                                                        } else listaPredDiaConcello[i].pchoiva.tarde.toString()
                                                            .plus(stringResource(id = R.string.porcentage))
                                                    )
                                                }
                                            }
                                        }
                                        Column {
                                            Text(
                                                text = stringResource(id = R.string.night),
                                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                                textAlign = TextAlign.Center,
                                                color = Color.White,

                                                )
                                            PutImage(
                                                iconSkyState(listaPredDiaConcello[i].ceo.noite),
                                                Modifier
                                                    .size(70.dp)
                                                    .align(Alignment.CenterHorizontally),
                                            )
                                            Row(
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                PutImage(
                                                    iconWindState(listaPredDiaConcello[i].vento.noite),
                                                    Modifier.size(35.dp)
                                                )

                                                Row(
                                                    modifier = Modifier,
                                                    verticalAlignment = Alignment.CenterVertically,


                                                    ) {
                                                    PutImage(
                                                        R.drawable.raindrop_measure,
                                                        Modifier.size(30.dp)
                                                    )
                                                    Text(
                                                        color = Color.White,
                                                        text = if (listaPredDiaConcello[i].pchoiva.noite.toString() == "-9999") {
                                                            stringResource(R.string.dash)
                                                        } else listaPredDiaConcello[i].pchoiva.noite.toString()
                                                            .plus(stringResource(id = R.string.porcentage))
                                                    )
                                                }
                                            }
                                        }
                                    }
                                    HorizontalDivider(
                                        modifier = Modifier.padding(top = 8.dp),
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }

                //Largo plazo

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Gray.copy(alpha = 0.7f))
                        .padding(8.dp),
                    color = Color.White,
                    text = stringResource(R.string.longTerm),
                    textAlign = TextAlign.Center
                )

                Column(
                    modifier = Modifier
                        .background(Color.LightGray.copy(alpha = 0.3f))
                ) {
                    val listaPredLargoPrazo = viewModel.concelloLargoPrazo.value.listaPredLargoPrazo
                    if (listaPredLargoPrazo.isNotEmpty()) {
                        listaPredLargoPrazo.forEach {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        color = Color.White,
                                        text = LocalDateTime.parse(it.dataPredicion).dayOfMonth.toString()
                                            .plus(" ").plus(
                                                monthName(LocalDateTime.parse(it.dataPredicion).month.value).take(
                                                    3
                                                )
                                            )
                                    )
                                }
                                Row {
                                    Column(
                                        modifier = Modifier.padding(horizontal = 16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        PutImage(iconSkyState(it.icoCeo1), Modifier.size(40.dp))
                                        Text(
                                            color = Color.White,
                                            text = it.probIcoCeo1.toString()
                                                .plus(stringResource(id = R.string.porcentage))
                                        )
                                    }
                                    Column(
                                        modifier = Modifier.padding(horizontal = 16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        PutImage(iconSkyState(it.icoCeo2), Modifier.size(40.dp))
                                        Text(
                                            color = Color.White,
                                            text = it.probIcoCeo2.toString()
                                                .plus(stringResource(id = R.string.porcentage))
                                        )
                                    }
                                    Column(
                                        modifier = Modifier.padding(horizontal = 16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        PutImage(iconSkyState(it.icoCeo3), Modifier.size(40.dp))
                                        Text(
                                            color = Color.White,
                                            text = it.probIcoCeo3.toString()
                                                .plus(stringResource(id = R.string.porcentage))
                                        )
                                    }
                                }
                                Column {
                                    PutImage(iconWindState(it.icoVento), Modifier.size(50.dp))
                                }
                                Column {
                                    Row {
                                        PutImage(R.drawable.max, Modifier.size(20.dp))
                                        Text(
                                            color = Color.White,
                                            text = it.tMin.toString()
                                                .plus(stringResource(id = R.string.deg))
                                        )
                                    }
                                    Row {
                                        PutImage(R.drawable.min, Modifier.size(20.dp))
                                        Text(
                                            color = Color.White,
                                            text = it.tMin.toString()
                                                .plus(stringResource(id = R.string.deg))
                                        )
                                    }
                                }
                            }

                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp),
                                color = Color.LightGray
                            )
                        }
                    }
                }
            }

            val listaAvisos = viewModel.concelloAvisos.value.listaPrediccionAvisos
            val maxNivel =
                if (listaAvisos.isNotEmpty()) {
                    val listaNiveles: MutableList<Int> = mutableListOf(0)
                    listaAvisos.forEach { l ->
                        l.listaAvisosConcellos.forEach { dia ->
                            listaNiveles.add(dia.idNivel)
                        }
                    }
                    listaNiveles.max()
                } else 0

            if (maxNivel != 0) {
                Card(

                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 84.dp)
                        .size(48.dp),
                    shape = RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = 0.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 8.dp
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .background(colorAviso(maxNivel))
                            .size(48.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Sharp.Warning,
                            contentDescription = "Pantalla 1",
                            modifier = Modifier.size(20.dp)
                        )
                    }

                }
            }

        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavigationBar(
    pagerState: PagerState,
    coroutineScope: CoroutineScope
) {
    BottomNavigation(
        modifier = Modifier.height(48.dp),
        backgroundColor = Gray
    ) {
        // Primer item de navegación (Pantalla 1)
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Pantalla 1") },
            selected = pagerState.currentPage == 0,
            onClick = {
                coroutineScope.launch {
                    pagerState.scrollToPage(0)
                }
            }
        )

        // Segundo item de navegación (Pantalla 2)
        BottomNavigationItem(
            icon = { Icon(Icons.Sharp.Warning, contentDescription = "Pantalla 2") },
            selected = pagerState.currentPage == 1,
            onClick = {
                coroutineScope.launch {
                    pagerState.scrollToPage(1)
                }
            }
        )
    }
}