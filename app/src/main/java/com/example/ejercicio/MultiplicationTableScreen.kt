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
fun MultiplicationTableScreen() {
    var number by remember { mutableStateOf("") }
    var results by remember { mutableStateOf<List<String>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tabla de Multiplicar",
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
                results = when {
                    num == null -> listOf("Ingresa un número entero válido.")
                    num < 0    -> listOf("El número no puede ser negativo.")
                    else       -> (1..12).map { i -> "$num x $i = ${num * i}" }
                }
                number = "" // limpiar campo después de generar tabla
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text("Generar Tabla")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (results.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column {
                    results.forEach { rowText ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (rowText.contains("válido") || rowText.contains("negativo")) {
                                Text(
                                    text = rowText,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.Red,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            } else {
                                val parts = rowText.split("=")
                                Text(
                                    text = parts[0].trim(),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.White
                                )
                                Text(
                                    text = "= ${parts.getOrNull(1)?.trim() ?: ""}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


