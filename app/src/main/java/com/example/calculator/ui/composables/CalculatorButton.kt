package com.example.calculator.ui.composables

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

@Composable
fun CalculatorButton(
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        )
    ) {
        content()
    }
}