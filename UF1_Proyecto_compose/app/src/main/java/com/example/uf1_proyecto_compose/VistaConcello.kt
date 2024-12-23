package com.example.uf1_proyecto_compose


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.uf1_proyecto_compose.vistaConcello.BottomNavigationBar
import com.example.uf1_proyecto_compose.vistaConcello.VistaAvisosConcello
import com.example.uf1_proyecto_compose.vistaConcello.VistaPrediccionConcello
import com.example.uf1_proyecto_compose.vistaConcello.deserializers.ObservacionConcello
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

    val concelloNull = remember {
        ObservacionConcello(-9999, -9999, 0, "", 0.0, 0.0)
    }

    val viewValue = viewModelTiempo.concelloObservacion.value







    Box (modifier = Modifier) {
            VistaPrediccionConcello(viewModelTiempo)



//

        if (concelloNull == viewValue || viewValue == null) {
            Log.d("VistaConcello", "Cargando datos...")
            Column(
                modifier = Modifier.fillMaxSize().background(Color(0xFF87CEEB))

            ) {
                CircularProgressIndicator()
            }
        }
    }
}
