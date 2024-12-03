package com.example.uf1_proyecto_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uf1_proyecto_compose.vistaConcello.listaConcello
import com.example.uf1_proyecto_compose.ui.theme.UF1_Proyecto_composeTheme
import com.example.uf1_proyecto_compose.vistaConcello.Concello
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.delay
import java.nio.charset.Charset


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UF1_Proyecto_composeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
        val navController = rememberNavController()


        NavHost(navController = navController, startDestination = "pantalla_principal") {
            composable("pantalla_principal") {
                BusquedaConcello(navController)
            }
            composable(
                "vista_concello/{idConcello}",
                arguments = listOf(navArgument("idConcello"){type = NavType.IntType})
                ) { backStackEntry ->
                val idConcello = backStackEntry.arguments?.getInt("idConcello") ?: 0
                VistaConcello(ViewModelTiempo(LocalContext.current, idConcello))
            }
        }

}



@Composable
fun BusquedaConcello(navController: NavHostController) {
    val listaConcellos = LocalContext.current.assets.open("concellos.json")
        .readBytes().toString(
            Charset.defaultCharset()
        )

    val context = LocalContext.current

    val searchQuery = remember { mutableStateOf("") }

    val isLoading = remember { mutableStateOf(true) }

    val listaConcelloRemember = remember { mutableStateOf<List<Concello>>(emptyList()) }


    LaunchedEffect(Unit) {
        val loadedCities = mutableListOf<Concello>()
        delay(50)

        listaConcello(
            context.assets.open("concellos.json")
                .readBytes()
                .toString(Charset.defaultCharset())
        )
            .forEach { city ->
                delay(1)
                loadedCities.add(city)
                listaConcelloRemember.value = loadedCities
                if (loadedCities.size == listaConcello(listaConcellos).size) {
                    isLoading.value = false
                }

            }
    }
    val filteredCities = listaConcelloRemember.value
        .filter { it.name.contains(searchQuery.value, ignoreCase = true) }
        .sortedBy { it.name }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        TextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            label = { Text("Buscar Ciudad") },
            placeholder = { Text("Escribe una ciudad") },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { /* Acción de icono si se desea */ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar")
                }
            }
        )

        // Mostrar el CircularProgressIndicator mientras se cargan los datos
        if (isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )



        } else {
            // Lista filtrada de ciudades
            Column(
                modifier = Modifier
                    .verticalScroll(state = rememberScrollState())
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                filteredCities.forEach { ciudad ->
                    Card(
                        modifier = Modifier
                            .width(280.dp)
                            .padding(8.dp),
                        onClick = {
                            navController.navigate("vista_concello/${ciudad.id}")
                        }
                    ) {
                        Text(
                            text = ciudad.name,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UF1_Proyecto_composeTheme {
        Greeting("Android")
    }
}