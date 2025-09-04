package com.Eduardo.multiplastelas

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class converterTemperatura : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            converterTemp()
        }
    }
}

@Composable
fun converterTemp(){

    var tempDigitada = remember { mutableStateOf("") }
    var tempFarenheit = remember { mutableStateOf(0f) }

    fun calcularTemp(): Float {
        val tempCelsius = tempDigitada.value.toFloatOrNull() ?: 0f
        return (tempCelsius * 9/5) + 32
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(10.dp)
    ){
        Column (
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Conversor de Temperatura")

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = tempDigitada.value,
                onValueChange = {tempDigitada.value = it},
                label = { Text("Temperatura em Celsius")},
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick =
                    {
                        tempFarenheit.value = calcularTemp()
                    },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text("Calcular Farenheit")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card {
                Text("Temperatura em Farenheit: ${"%.1f ".format(tempFarenheit.value)}")
            }
        }



    }




}










