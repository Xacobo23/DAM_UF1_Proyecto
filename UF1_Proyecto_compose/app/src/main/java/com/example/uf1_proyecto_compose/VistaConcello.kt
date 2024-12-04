package com.example.uf1_proyecto_compose

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.funciones.PutImage
import com.example.uf1_proyecto_compose.vistaConcello.BottomNavigationBar
import com.example.uf1_proyecto_compose.vistaConcello.VistaAvisosConcello
import com.example.uf1_proyecto_compose.vistaConcello.VistaPrediccionConcello
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun VistaConcello(
    viewModelTiempo: ViewModelTiempo
) {
    val navController = rememberNavController()
    val pagerState = remember { PagerState() }
    val coroutineScope = rememberCoroutineScope()
    var visible by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }
    var topBarVisible by remember { mutableStateOf(true) }  // Estado para controlar visibilidad de la TopAppBar
    val scrollState = rememberScrollState()

    val drawerState = rememberDrawerState(DrawerValue.Closed) // Estado para controlar la visibilidad del Drawer
    val openDrawer = { suspend { drawerState.open()} } // Función para abrir el Drawer
    val closeDrawer = { suspend { drawerState.close() } } // Función para cerrar el Drawer

    // Simulando un delay para ocultar el contenido
    LaunchedEffect(visible) {
        delay(2000)
        visible = false
    }

    // Contenido del Drawer
    val drawerContent: @Composable () -> Unit = {
        Column(
            modifier = Modifier.fillMaxHeight().padding(16.dp)
        ) {
            Text("Opciones", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Item 1")
            Text("Item 2")
            Text("Item 3")
            // Añadir más opciones de navegación si es necesario
        }
    }

    Box {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = drawerContent,  // Este es el contenido del Drawer
            content = {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController,
                            pagerState = pagerState,
                            coroutineScope = coroutineScope
                        )
                    },
                    modifier = Modifier.pointerInput(Unit) {
                        detectVerticalDragGestures { _, verticalDragAmount ->
                            if (verticalDragAmount < 0) {
                                // Desplazamiento hacia arriba
                                topBarVisible = true
                            } else if (verticalDragAmount > 0) {
                                // Desplazamiento hacia abajo
                                topBarVisible = false
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
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
            }
        )

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
