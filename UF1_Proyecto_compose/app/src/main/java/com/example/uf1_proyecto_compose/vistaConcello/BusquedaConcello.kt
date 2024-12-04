package com.example.uf1_proyecto_compose.vistaConcello

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.vistaConcello.deserializers.Concello
import com.example.uf1_proyecto_compose.vistaConcello.deserializers.listaConcello
import kotlinx.coroutines.delay
import java.nio.charset.Charset

@Composable
fun BusquedaConcello(navController: NavHostController) {
    val listaConcellos = LocalContext.current.assets.open("concellos.json")
        .readBytes().toString(
            Charset.defaultCharset()
        )

    val searchQuery = remember { mutableStateOf("") }

    val isLoading = remember { mutableStateOf(true) }

    val listaConcelloRemember = remember { mutableStateOf<List<Concello>>(emptyList()) }


    LaunchedEffect(Unit) {
        val loadedCities = mutableListOf<Concello>()
        delay(50)

        listaConcello(listaConcellos)
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
        modifier = Modifier
            .background(Color(0xFF87CEEB))
            .padding(16.dp).safeContentPadding()

    ) {
        TextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            label = { Text(stringResource(R.string.findConcello)) },
            placeholder = { Text(stringResource(R.string.writeConcello)) },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp)),
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar")
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
            // Lista filtrada de concellos
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