package com.example.calculator.ui.composables

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.widget.Toast
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
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
    symbols: List<List<Symbol>>,
    modifier: Modifier = Modifier,
    onClick: (Symbol) -> Unit,
    getColors: (Int, Int, Int) -> List<Color>
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {
        repeat (symbols.size) { i ->
            Row(
                Modifier
                    .height(IntrinsicSize.Max)
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                for (j in 0 until symbols[i].size) {
                    val colors = getColors(i, j, cols)
                    val symbolColor = colors[1]
                    val backgroundColor = colors[0]
                    val symbol = symbols[i][j]

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
                                    "Failed: ${e.message}",
                                    Toast.LENGTH_SHORT,
                                ).show()
                            }
                        },
                    ) {
                        Text(
                            symbol.getSymbol(),
                            color = symbolColor,
                            fontSize = 20.sp,
                        )
                    }
                }
            }
        }
    }
}