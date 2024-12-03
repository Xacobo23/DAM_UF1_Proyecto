package com.example.uf1_proyecto_compose.funciones

import androidx.compose.ui.graphics.Color
import com.example.uf1_proyecto_compose.ui.theme.Orange
import com.example.uf1_proyecto_compose.ui.theme.Yellow

    fun colorAviso(i:Int): Color {
        return when (i){
            1 -> Yellow
            2 -> Orange
            3 -> Color.Red
            else -> Color.LightGray
        }
    }

    fun descripcionAlerta(idTipoAlerta: Int): String {
        return when (idTipoAlerta) {
            1 -> "Temperatura máxima"
            2 -> "Temperatura mínima"
            3 -> "Precipitación 1 hora"
            4 -> "Precipitación 12 horas"
            5 -> "Néboa"
            6 -> "Neve"
            7 -> "Tormenta"
            8 -> "Refacho máximo de vento"
            9 -> "Vento no mar"
            10 -> "Ondas"
            else -> "Tipo de alerta desconocido"
        }
    }

