package com.example.calculator.ui.composables

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.calculator.core.Symbol
import com.example.calculator.ui.theme.Black
import com.example.calculator.ui.theme.Carbon
import com.example.calculator.ui.theme.QuickSilver
import com.example.calculator.ui.theme.VitaminC
import com.example.calculator.ui.theme.White

private fun getColorsPortrait(i: Int, j: Int, cols: Int): List<Color> {
    val res = mutableListOf(Carbon, White)
    if (j == cols - 1) {
        res[0] = VitaminC
    } else if (i == 0) {
        res[0] = QuickSilver
        res[1] = Black
    }
    return res
}

private fun getColorsLandscape(i: Int, j: Int, cols: Int): List<Color> {
    val res = mutableListOf(Carbon, White)
    if (j == cols - 1) {
        res[0] = VitaminC
    } else if (j > 2) {
        res[0] = QuickSilver
        res[1] = Black
    }
    return res
}

@Composable
fun NumPad(
    cols: Int,
    symbols: List<Symbol>,
    modifier: Modifier = Modifier,
    onClick: (Symbol) -> Unit,
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    var getColors = ::getColorsPortrait
    if (configuration.orientation != ORIENTATION_PORTRAIT) {
        getColors = ::getColorsLandscape
    }
    val rows = symbols.size / cols

    Column(
        modifier = modifier
    ) {
        repeat (rows) { i ->
            Row(
                Modifier
                    .height(IntrinsicSize.Max)
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                for (j in 0 until cols) {
                    val colors = getColors(i, j, cols)
                    val symbolColor = colors[1]
                    val backgroundColor = colors[0]
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
                        )
                    }
                }
            }
        }
    }
}