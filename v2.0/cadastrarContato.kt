package Telas.com

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class cadastrarContato : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            formulario()
        }
    }

class Contato(
    var nome: String,
    var fone: String,
    var email: String
){
    init {
        if (nome.isEmpty()){
            nome = "Não Informado"
        }
        if (fone.isEmpty()){
            fone = "Não informado"
        }
        if (email.isEmpty()){
            email = "Não informado"
        }
    }

        override fun toString(): String {
            return "Nome: $nome,\nFone: $fone,\nEmail: $email)"
        }
    }
}

@Preview
@Composable
fun formulario(){

    val nome = remember { mutableStateOf("") }
    val fone = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    val ultimoContato = remember { mutableStateOf<cadastrarContato.Contato?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Column (
            modifier = Modifier.align(Alignment.Center)
        ){
            Text("Cadastrar novo contato",
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                ))
            Spacer(
                modifier = Modifier
                    .height(15.dp)
            )
            TextField(
                value = nome.value,
                onValueChange = {nome.value = it},
                label = {Text("Nome: ")}
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            TextField(
                value = fone.value,
                onValueChange = {fone.value = it},
                label = {Text("Fone: ")}
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            TextField(
                value = email.value,
                onValueChange = {email.value = it},
                label = {Text("Email: ")}
            )
            Spacer(
                modifier = Modifier.height(15.dp)
            )

            Button(
                onClick = {
                    ultimoContato.value = cadastrarContato.Contato(
                        nome.value,
                        fone.value,
                        email.value
                    )

                    // Se quiser, pode limpar os campos depois:
                    nome.value = ""
                    fone.value = ""
                    email.value = ""
                },
                modifier = Modifier
                    .width(150.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                Text("Salvar",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                    ))

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar",
                )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card (
                modifier = Modifier.width(280.dp).height(150.dp)
            ){
                Text("Último contato salvo:",
                    modifier = Modifier.padding(10.dp))

                Spacer(modifier = Modifier.height(15.dp))

                if (ultimoContato.value != null) {
                    Text(ultimoContato.value.toString(),
                        modifier = Modifier.padding(10.dp))
                } else {
                    Text("Nenhum contato salvo ainda",
                        modifier = Modifier.padding(10.dp))
                }
            }


        }
    }
}





