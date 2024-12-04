package com.example.uf1_proyecto_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uf1_proyecto_compose.vistaConcello.deserializers.listaConcello
import com.example.uf1_proyecto_compose.ui.theme.UF1_Proyecto_composeTheme
import com.example.uf1_proyecto_compose.vistaConcello.BusquedaConcello
import com.example.uf1_proyecto_compose.vistaConcello.deserializers.Concello
import kotlinx.coroutines.delay
import java.nio.charset.Charset


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            UF1_Proyecto_composeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
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
            arguments = listOf(navArgument("idConcello") { type = NavType.IntType })
        ) { backStackEntry ->
            val idConcello = backStackEntry.arguments?.getInt("idConcello") ?: 0
            VistaConcello(ViewModelTiempo(LocalContext.current, idConcello))
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