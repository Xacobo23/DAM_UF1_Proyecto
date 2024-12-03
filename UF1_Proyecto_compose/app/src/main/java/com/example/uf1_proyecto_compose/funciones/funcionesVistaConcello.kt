package com.example.uf1_proyecto_compose.funciones

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.vistaConcello.Horas
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter



    @Composable
    fun PutImage(i: Int?, modifier: Modifier) {
        i?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = stringResource(id = R.string.svgIcon),
                modifier,
                contentScale = ContentScale.Fit
            )
        }
    }


    @Composable
    fun weekDay(): String {
        return when (LocalDate.now().dayOfWeek.value) {
            1 -> stringResource(id = R.string.monday)
            2 -> stringResource(id = R.string.tuesday)
            3 -> stringResource(id = R.string.wednesday)
            4 -> stringResource(id = R.string.thursday)
            5 -> stringResource(id = R.string.friday)
            6 -> stringResource(id = R.string.saturday)
            7 -> stringResource(id = R.string.sunday)

            else -> stringResource(id = R.string.error)
        }
    }

    @Composable
    fun weekDay(i: Int): String {
        return when (i) {
            1 -> stringResource(id = R.string.monday)
            2 -> stringResource(id = R.string.tuesday)
            3 -> stringResource(id = R.string.wednesday)
            4 -> stringResource(id = R.string.thursday)
            5 -> stringResource(id = R.string.friday)
            6 -> stringResource(id = R.string.saturday)
            7 -> stringResource(id = R.string.sunday)

            else -> stringResource(id = R.string.error)
        }
    }

    @Composable
    fun monthName(): String {
        return when (LocalDate.now().month.value) {
            1 -> stringResource(id = R.string.january)
            2 -> stringResource(id = R.string.february)
            3 -> stringResource(id = R.string.march)
            4 -> stringResource(id = R.string.april)
            5 -> stringResource(id = R.string.may)
            6 -> stringResource(id = R.string.june)
            7 -> stringResource(id = R.string.july)
            8 -> stringResource(id = R.string.august)
            9 -> stringResource(id = R.string.september)
            10 -> stringResource(id = R.string.october)
            11 -> stringResource(id = R.string.november)
            12 -> stringResource(id = R.string.december)


            else -> stringResource(id = R.string.error)
        }
    }

    @Composable
    fun monthName(i: Int): String {
        return when (i) {
            1 -> stringResource(id = R.string.january)
            2 -> stringResource(id = R.string.february)
            3 -> stringResource(id = R.string.march)
            4 -> stringResource(id = R.string.april)
            5 -> stringResource(id = R.string.may)
            6 -> stringResource(id = R.string.june)
            7 -> stringResource(id = R.string.july)
            8 -> stringResource(id = R.string.august)
            9 -> stringResource(id = R.string.september)
            10 -> stringResource(id = R.string.october)
            11 -> stringResource(id = R.string.november)
            12 -> stringResource(id = R.string.december)


            else -> stringResource(id = R.string.error)
        }
    }


    fun iconSkyState(i: Int): Int {
        val iconMap = mapOf(
            101 to R.drawable.t_101, 102 to R.drawable.t_102, 103 to R.drawable.t_103,
            104 to R.drawable.t_104, 105 to R.drawable.t_105, 106 to R.drawable.t_106,
            107 to R.drawable.t_107, 108 to R.drawable.t_108, 109 to R.drawable.t_109,
            110 to R.drawable.t_110, 111 to R.drawable.t_111, 112 to R.drawable.t_112,
            113 to R.drawable.t_113, 114 to R.drawable.t_114, 115 to R.drawable.t_115,
            116 to R.drawable.t_116, 117 to R.drawable.t_117, 118 to R.drawable.t_118,
            119 to R.drawable.t_119, 120 to R.drawable.t_120, 121 to R.drawable.t_121,

            201 to R.drawable.t_201, 202 to R.drawable.t_202, 203 to R.drawable.t_203,
            204 to R.drawable.t_204, 205 to R.drawable.t_205, 206 to R.drawable.t_206,
            207 to R.drawable.t_207, 208 to R.drawable.t_208, 209 to R.drawable.t_209,
            210 to R.drawable.t_210, 211 to R.drawable.t_211, 212 to R.drawable.t_212,
            213 to R.drawable.t_213, 214 to R.drawable.t_214, 215 to R.drawable.t_215,
            216 to R.drawable.t_216, 217 to R.drawable.t_217, 218 to R.drawable.t_218,
            219 to R.drawable.t_219, 220 to R.drawable.t_220, 221 to R.drawable.t_221,

            )
        return iconMap[i] ?: R.drawable.t_9999
    }

    fun iconWindState(i: Int): Int {
        val iconMap = mapOf(
            299 to R.drawable.t_299, 300 to R.drawable.t_300, 301 to R.drawable.t_301,
            302 to R.drawable.t_302, 303 to R.drawable.t_303, 304 to R.drawable.t_304,
            305 to R.drawable.t_305, 306 to R.drawable.t_306, 307 to R.drawable.t_307,
            308 to R.drawable.t_308, 309 to R.drawable.t_309, 310 to R.drawable.t_310,
            311 to R.drawable.t_311, 312 to R.drawable.t_312, 313 to R.drawable.t_313,
            314 to R.drawable.t_314, 315 to R.drawable.t_315, 316 to R.drawable.t_316,
            317 to R.drawable.t_317, 318 to R.drawable.t_318, 319 to R.drawable.t_319,
            320 to R.drawable.t_320, 321 to R.drawable.t_321, 322 to R.drawable.t_322,
            323 to R.drawable.t_323, 324 to R.drawable.t_324, 325 to R.drawable.t_325,
            326 to R.drawable.t_326, 327 to R.drawable.t_327, 328 to R.drawable.t_328,
            329 to R.drawable.t_329, 330 to R.drawable.t_330, 331 to R.drawable.t_331,
            332 to R.drawable.t_332
        )
        return iconMap[i] ?: R.drawable.t_9999
    }


    fun backgroundBrush(horas: Horas): Brush {
        // Día
        if (getHora(horas.sunrise).plusMinutes(10) <= LocalTime.now() &&
            LocalTime.now() <= getHora(horas.sunset).minusMinutes(10)
        ) {
            return Brush.linearGradient(
                listOf(
                    Color(0xFF87CEEB),  // Azul cielo claro
                    Color(0xFF4682B4),  // Azul acero
                    Color(0xFFB0C4DE)   // Azul pálido
                )
            )
        }

        // Atardecer
        else if (getHora(horas.sunset).minusMinutes(10) < LocalTime.now() &&
            LocalTime.now() < getHora(horas.sunset).plusMinutes(10)
        ) {
            return Brush.linearGradient(
                listOf(
                    Color(0xFFFF7F50),  // Coral
                    Color(0xFFFF4500),  // Naranja brillante
                    Color(0xFF8B0000)   // Rojo oscuro
                )
            )
        }

        // Amanecer
        else if (getHora(horas.sunrise).minusMinutes(10) < LocalTime.now() &&
            LocalTime.now() < getHora(horas.sunrise).plusMinutes(10)
        ) {
            return Brush.linearGradient(
                listOf(
                    Color(0xFFFFD700),  // Amarillo dorado
                    Color(0xFFFFA500),  // Naranja cálido
                    Color(0xFFFF4500)   // Rojo anaranjado
                )
            )
        }

        //Noite
        else return Brush.linearGradient(
            listOf(
                Color(0xFF1E90FF),  // Azul celeste
                Color(0xFF00008B),  // Azul marino oscuro
                Color(0xFF000000)   // Negro para la noche
            )
        )

    }


    private fun getHora(s: String): LocalTime {
        val h = s.split(" ")

        val timeAMPM = LocalTime.parse(h[0], DateTimeFormatter.ofPattern("H:mm:ss"))
        return if (h.size == 2) {
            if (h[1] == "PM" && timeAMPM.hour != 12) LocalTime.of(
                timeAMPM.hour,
                timeAMPM.minute,
                timeAMPM.second
            ).plusHours(12)
            else LocalTime.of(timeAMPM.hour, timeAMPM.minute, timeAMPM.second)

        } else LocalTime.of(0, 0, 0)

    }

