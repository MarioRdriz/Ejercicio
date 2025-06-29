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

@Composable
fun FactorialScreen() {
    var number by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calcular Factorial",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto con fondo negro
        OutlinedTextField(
            value = number,
            onValueChange = { number = it },
            label = { Text("Ingresa un número entero positivo", color = Color.White) },
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
                val num = number.toIntOrNull()
                result = when {
                    num == null -> "Por favor, ingresa un número entero válido."
                    num < 0 -> "El número debe ser positivo."
                    num > 20 -> "El número es demasiado grande para calcular el factorial."
                    else -> {
                        try {
                            "Factorial de $num = ${calculateFactorial(num)}"
                        } catch (e: Exception) {
                            "Error al calcular el factorial: ${e.message}"
                        }
                    }
                }
                number = "" // limpiar campo después de calcular
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text("Calcular Factorial")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Resultado en fondo negro
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = result,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}

fun calculateFactorial(n: Int): Long {
    if (n == 0 || n == 1) return 1L
    return n * calculateFactorial(n - 1)
}
