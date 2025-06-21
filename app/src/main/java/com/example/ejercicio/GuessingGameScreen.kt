package com.example.ejercicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun GuessingGameScreen() {
    var guess by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("Adivinemos el número del 1-10!") }
    var secretNumber by remember { mutableIntStateOf(Random.nextInt(1, 11)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Juego de Adivinanza",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto con fondo negro
        OutlinedTextField(
            value = guess,
            onValueChange = { guess = it },
            label = { Text("Ingresa tu número (1 a 10)", color = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black, shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Black,
                unfocusedContainerColor = Color.Black,
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón con fondo negro
        Button(
            onClick = {
                val userGuess = guess.toIntOrNull()
                message = if (userGuess != null && userGuess in 1..10) {
                    when {
                        userGuess == secretNumber -> {
                            val msg = "¡Correcto! El número era $secretNumber. ¡Juega de nuevo!"
                            secretNumber = Random.nextInt(1, 11)
                            guess = ""
                            msg
                        }
                        userGuess < secretNumber -> {
                            guess = ""
                            "El número es mayor."
                        }
                        else -> {
                            guess = ""
                            "El número es menor."
                        }
                    }
                } else {
                    guess = ""
                    "Por favor, ingresa un número válido entre 1 y 10."
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text("Adivinar")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje en fondo negro
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}

