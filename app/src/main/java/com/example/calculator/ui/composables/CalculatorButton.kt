package com.example.calculator.ui.composables

import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit

@Composable
fun CalculatorButton(
    backgroundColor: Color,
    onClick: () -> Unit,
    label: String,
    textColor: Color,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Button(
        onClick = {
            try {
                onClick()
            } catch (e: Exception) {
                Toast.makeText(context, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        },
        modifier = modifier,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
    ) {
        Text(text = label, color = textColor, fontSize = fontSize)
    }
}