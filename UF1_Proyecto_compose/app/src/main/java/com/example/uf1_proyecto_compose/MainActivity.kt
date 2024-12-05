package com.example.uf1_proyecto_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uf1_proyecto_compose.ui.theme.UF1_Proyecto_composeTheme
import com.example.uf1_proyecto_compose.vistaConcello.BusquedaConcello



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            UF1_Proyecto_composeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
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