package com.Eduardo.multiplastelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Formulario()
        }
    }
}

//Clase Folha de pagamento ---------------------------------------------------

class FolhaPagamento(
    var nome: String,
    var horasTrabalhadas: Float,
    var valorHora: Float
){
    fun calcularSalarioBruto():Float{
        return horasTrabalhadas * valorHora
    }

    fun calcularINSS(): Float{
        return calcularSalarioBruto() * 0.08f
    }

    fun calcularIR():Float{
        return calcularSalarioBruto() * 0.09f
    }

    fun calcularSalarioLiq():Float{
        return calcularSalarioBruto() - calcularIR() - calcularINSS()
    }
}

//Folha de Pagamento ------------------------------------------------------
@Composable
fun Formulario() {

    val nome = remember { mutableStateOf("") }
    val horasTrabalhadas = remember { mutableStateOf("") }
    val valorHora = remember { mutableStateOf("") }
    val salarioLiquido = remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {

            Text("Folha de Pagamento")

            Spacer(modifier = Modifier.height(5.dp))

            TextField(
                value = nome.value,
                onValueChange = { nome.value = it },
                label = { Text("Nome do funcionário") }
            )
            Spacer(modifier = Modifier.height(5.dp))

            TextField(
                value = horasTrabalhadas.value,
                onValueChange = { horasTrabalhadas.value = it },
                label = { Text("Horas Trabalhadas") }
            )
            Spacer(modifier = Modifier.height(5.dp))

            TextField(
                value = valorHora.value,
                onValueChange = { valorHora.value = it },
                label = { Text("Valor da hora") }
            )
            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = {
                    val horas = horasTrabalhadas.value.toFloatOrNull() ?: 0f
                    val valor = valorHora.value.toFloatOrNull() ?: 0f
                    val folhaPagamento = FolhaPagamento(nome.value, horas, valor)
                    salarioLiquido.value = folhaPagamento.calcularSalarioLiq()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Text("Calcular horas de trabalho")
            }

            Spacer(modifier = Modifier.height(5.dp))

            Card(modifier = Modifier.padding(8.dp)) {
                Text("Salário Líquido: R$ ${"%.2f".format(salarioLiquido.value)}")
            }

        }
    }
}








