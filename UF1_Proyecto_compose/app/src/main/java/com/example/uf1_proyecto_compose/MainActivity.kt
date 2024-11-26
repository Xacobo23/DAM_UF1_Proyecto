package com.example.uf1_proyecto_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.uf1_proyecto_compose.ui.theme.UF1_Proyecto_composeTheme
import java.nio.charset.Charset

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UF1_Proyecto_composeTheme {
                // A surface container using the 'background' color from the theme
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
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface {
        ListConcello(modifier = Modifier.fillMaxSize())

    }
}



@Composable
fun ListConcello(modifier: Modifier = Modifier){
    val listaConcellos = LocalContext.current.assets.open("concellos.json")
        .readBytes().toString(
            Charset.defaultCharset()
        )
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        listaConcello(listaConcellos).forEach{
            item {
                    Text(text = it.name)
//


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