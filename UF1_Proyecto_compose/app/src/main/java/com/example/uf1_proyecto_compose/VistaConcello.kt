package com.example.uf1_proyecto_compose


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.vistaConcello.BottomNavigationBar
import com.example.uf1_proyecto_compose.vistaConcello.VistaAvisosConcello
import com.example.uf1_proyecto_compose.vistaConcello.VistaPrediccionConcello
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun VistaConcello(
    viewModelTiempo: ViewModelTiempo,
) {

    val pagerState = remember { PagerState() }
    val coroutineScope = rememberCoroutineScope()
    var visible by remember { mutableStateOf(true) }


    // Simulando un delay para ocultar el contenido
    LaunchedEffect(visible) {
        delay(2000)
        visible = false
    }



    Box (modifier = Modifier) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    pagerState = pagerState,
                    coroutineScope = coroutineScope
                )
            },
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Box(modifier = Modifier) {
                HorizontalPager(
                    state = pagerState,
                    count = 2, // Número de pantallas
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    when (page) {
                        0 -> VistaPrediccionConcello(viewModelTiempo) // Primera pantalla
                        1 -> VistaAvisosConcello(viewModelTiempo) // Segunda pantalla
                    }
                }
            }
        }



        if (visible) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color(0xFF87CEEB))
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
