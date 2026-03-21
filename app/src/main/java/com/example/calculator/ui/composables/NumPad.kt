package com.example.calculator.ui.composables

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.widget.Toast
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.core.Symbol
import com.example.calculator.ui.theme.Black
import com.example.calculator.ui.theme.Carbon
import com.example.calculator.ui.theme.QuickSilver
import com.example.calculator.ui.theme.VitaminC
import com.example.calculator.ui.theme.White

@Composable
fun NumPad(
    cols: Int,
    symbols: List<Symbol>,
    onClick: (Symbol) -> Unit,
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    var textPadding = 20.dp
    if (configuration.orientation != ORIENTATION_PORTRAIT) {
        textPadding = 0.dp
    }

    LazyColumn() {
        items ((symbols.size) / cols) { i ->
            Row(
                Modifier
                    .height(IntrinsicSize.Max)
                    .fillMaxWidth()
            ) {
                for (j in 0 until cols) {
                    var symbolColor = White
                    var backgroundColor = Carbon
                    if (j == cols - 1) {
                        backgroundColor = VitaminC
                    } else if (i == 0) {
                        backgroundColor = QuickSilver
                        symbolColor = Black
                    }
                    val symbol = symbols[i * cols + j]
                    CalculatorButton(
                        backgroundColor = backgroundColor,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        onClick = {
                            try {
                                onClick(symbol)
                            } catch (e: Exception) {
                                Toast.makeText(
                                    context,
                                    "Failed to evaluate expression",
                                    Toast.LENGTH_SHORT,
                                ).show()
                            }
                        }
                    ) {
                        Text(
                            symbol.getSymbol(),
                            color = symbolColor,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(vertical = textPadding),
                        )
                    }
                }
            }
        }
    }
}