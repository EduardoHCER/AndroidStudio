package Telas.com

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TelaDeTarefas()
        }
    }
}


@Preview
@Composable
fun Cabecalho() {

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
    {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize().padding(10.dp)
        ){
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Icone Perfil",
                modifier = Modifier.size(50.dp)

            )
            Spacer(
                modifier = Modifier.width(15.dp)
            )
            Column {

                Text(
                    text = "Eduardo Rech",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Programando card de perfil",
                    style = MaterialTheme.typography.bodySmall
                )
            }


        }
    }
}
@Composable
fun Formulario( onAdd:(String) -> Unit ){

    var textoDigitado by remember {
        mutableStateOf("")
    }

    Row (
    ){
        TextField(
            value = textoDigitado,
            onValueChange = {textoDigitado = it },
            label = { Text("Digite algo aqui...")}
        )

        Spacer(modifier = Modifier.width(10.dp))

        Button(
            onClick = {
                onAdd(textoDigitado) //Manda a tarefa para o retorno do botao
                textoDigitado = "" //Limpa o campo
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 34, green = 139, blue = 34),
                contentColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Icone add"
            )
            Text("Add")
        }
    }
}

@Composable
fun Tarefa(texto: String = "Tarefa", onDelete: (String) -> Unit) {

    var marcado by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = if (marcado) CardDefaults.cardColors(Color(red = 34, green = 139, blue = 34)) else CardDefaults.cardColors(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight().padding(horizontal = 10.dp)
        ) {
            Icon(
                imageVector = if (marcado) Icons.Default.Check else Icons.Default.Favorite,
                contentDescription = "Ícone favorito",
                modifier = Modifier.size(25.dp).clickable { marcado = !marcado }
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = texto,
                style = MaterialTheme.typography.bodyMedium
            )

            // Spacer que ocupa o espaço restante e empurra o delete pro final
            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Deletar",
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        onDelete(texto)
                    }
            )
        }
    }
}

@Preview
@Composable
fun TelaDeTarefas(){

    var listaTarefas by remember {
        mutableStateOf(listOf(""))
    }

    //Função interna para adicionar tarefa
    fun adicionarTarefa(tarefa:String){
        listaTarefas = listaTarefas + tarefa
    }

    fun deletarTarefa(tarefa:String){
        listaTarefas = listaTarefas - tarefa
    }

    Scaffold (
        containerColor = Color.LightGray,
    ){

            innerPadding ->

        Column (
            modifier = Modifier.padding(innerPadding).padding(horizontal = 5.dp)
        )
        {
            Cabecalho()
            Spacer(modifier = Modifier.height(10.dp))
            Formulario(onAdd = {
                    novaTarefa -> adicionarTarefa(novaTarefa)
            })
            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {
                items(listaTarefas){
                        tar ->
                    Tarefa(tar, onDelete = {
                        deletarTarefa(tar)
                    })
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }

        }
    }
}