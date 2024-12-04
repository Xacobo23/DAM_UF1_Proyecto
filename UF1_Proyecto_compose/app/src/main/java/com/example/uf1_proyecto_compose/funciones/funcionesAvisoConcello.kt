package com.example.uf1_proyecto_compose.funciones

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.ui.theme.Orange
import com.example.uf1_proyecto_compose.ui.theme.Yellow

fun colorAviso(i: Int): Color {
    return when (i) {
        1 -> Yellow
        2 -> Orange
        3 -> Color.Red
        else -> Color.LightGray
    }
}

@Composable
fun descripcionAlerta(idTipoAlerta: Int): String {
    return when (idTipoAlerta) {
        1 -> stringResource(R.string.alert1)
        2 -> stringResource(R.string.alert2)
        3 -> stringResource(R.string.alert3)
        4 -> stringResource(R.string.alert4)
        5 -> stringResource(R.string.alert5)
        6 -> stringResource(R.string.alert6)
        7 -> stringResource(R.string.alert7)
        8 -> stringResource(R.string.alert8)
        9 -> stringResource(R.string.alert9)
        10 -> stringResource(R.string.alert10)
        else -> stringResource(R.string.alertElse)
    }
}

