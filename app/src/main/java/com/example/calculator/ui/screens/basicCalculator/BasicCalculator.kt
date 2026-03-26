package com.example.calculator.ui.screens.basicCalculator

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.core.CalculatorViewModel
import com.example.calculator.core.Symbol
import com.example.calculator.ui.composables.CalculatorButton
import com.example.calculator.ui.theme.Black
import com.example.calculator.ui.theme.Carbon
import com.example.calculator.ui.theme.QuickSilver
import com.example.calculator.ui.theme.VitaminC
import com.example.calculator.ui.theme.White

@Composable
fun BasicCalculator(
    calculatorViewModel: CalculatorViewModel = viewModel<CalculatorViewModel>()
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val state = calculatorViewModel.state.collectAsState()
    val currNumberScrollState = rememberScrollState()
    val expressionScrollState = rememberScrollState()

    val cols = 4
    val numPadSymbols = listOf(
        Symbol.ClearAll, Symbol.Clear, Symbol.ToggleSign, Symbol.Divide,
        Symbol.Seven, Symbol.Eight, Symbol.Nine, Symbol.Multiply,
        Symbol.Four, Symbol.Five, Symbol.Six, Symbol.Subtract,
        Symbol.One, Symbol.Two, Symbol.Three, Symbol.Add,
        Symbol.DecimalPoint, Symbol.Zero, Symbol.Backspace, Symbol.Evaluate,
    )

    LaunchedEffect(state.value.currNumber) {
        currNumberScrollState.animateScrollTo(currNumberScrollState.maxValue)
    }

    LaunchedEffect(state.value.numberStack, state.value.operatorStack) {
        expressionScrollState.animateScrollTo(expressionScrollState.maxValue)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        padding -> Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.End,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = calculatorViewModel.toString(),
                        fontSize = if (configuration.orientation == ORIENTATION_PORTRAIT) 20.sp else 16.sp,
                        modifier = Modifier
                            .horizontalScroll(expressionScrollState),
                        color = QuickSilver,
                    )
                }

                Text(
                    text = state.value.currNumber.toString(),
                    fontSize = if (configuration.orientation == ORIENTATION_PORTRAIT) 32.sp else 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(currNumberScrollState),
                    textAlign = TextAlign.Right,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(if (configuration.orientation == ORIENTATION_PORTRAIT) 4f else 3f),
            ) {
                repeat(numPadSymbols.size / cols) { row ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        repeat(cols) { col ->
                            val symbol = numPadSymbols[row * cols + col]
                            var backgroundColor = Carbon
                            var textColor = White
                            if (row == 0) {
                                backgroundColor = QuickSilver
                                textColor = Black
                            } else if (col == cols - 1) {
                                backgroundColor = VitaminC
                            }

                            CalculatorButton(
                                backgroundColor = backgroundColor,
                                onClick = {
                                    try {
                                        calculatorViewModel.onClick(symbol)
                                    } catch (e: Exception) {
                                        Toast.makeText(
                                            context,
                                            "Failed: ${e.message}",
                                            Toast.LENGTH_SHORT,
                                        ).show()
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                            ) {
                                Text(symbol.getSymbol(), color = textColor)
                            }
                        }
                    }
                }
            }
        }
    }
}