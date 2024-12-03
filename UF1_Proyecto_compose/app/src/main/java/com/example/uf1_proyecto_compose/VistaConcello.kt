package com.example.uf1_proyecto_compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.vistaConcello.BottomNavigationBar
import com.example.uf1_proyecto_compose.vistaConcello.VistaAvisosConcello
import com.example.uf1_proyecto_compose.vistaConcello.VistaPrediccionConcello
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun VistaConcello(
    viewModelTiempo: ViewModelTiempo
) {
    Surface {

        val navController = rememberNavController()
        val pagerState = remember { PagerState() } // Estado del pager
        val coroutineScope = rememberCoroutineScope()



        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    navController = navController,
                    pagerState = pagerState,
                    coroutineScope = coroutineScope
                ) // Barra inferior
            }
        ) { innerPadding ->

            // Configuración de la navegación con HorizontalPager
            Box(modifier = Modifier.padding(innerPadding)) {
                HorizontalPager(
                    state = pagerState,
                    count = 2, // Número de pantallas
                    modifier = Modifier.fillMaxSize() // Asegura que ocupe todo el espacio disponible
                ) { page ->
                    when (page) {
                        0 -> VistaPrediccionConcello(viewModelTiempo) // Primera pantalla
                        1 -> VistaAvisosConcello(viewModelTiempo) // Segunda pantalla
                    }
                }
            }
        }
    }
}
