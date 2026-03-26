package com.example.calculator.ui.screens.basicCalculator

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.core.CalculatorViewModel
import com.example.calculator.core.Symbol
import com.example.calculator.ui.composables.NumPad

@Composable
fun BasicCalculator(
    calculatorViewModel: CalculatorViewModel = viewModel<CalculatorViewModel>()
) {
    val configuration = LocalConfiguration.current

    val state = calculatorViewModel.state.collectAsState()
    val currNumberScrollState = rememberScrollState()
    val expressionScrollState = rememberScrollState()

    LaunchedEffect(state.value.currNumber) {
        currNumberScrollState.animateScrollTo(currNumberScrollState.maxValue)
    }

    LaunchedEffect(state.value.numberStack, state.value.operatorStack) {
        expressionScrollState.animateScrollTo(expressionScrollState.maxValue)
    }

    var cols = 4
    var numPadSymbols = listOf(
        Symbol.ClearAll, Symbol.Clear, Symbol.ToggleSign, Symbol.Divide,
        Symbol.Seven, Symbol.Eight, Symbol.Nine, Symbol.Multiply,
        Symbol.Four, Symbol.Five, Symbol.Six, Symbol.Subtract,
        Symbol.One, Symbol.Two, Symbol.Three, Symbol.Add,
        Symbol.DecimalPoint, Symbol.Zero, Symbol.Backspace, Symbol.Evaluate,
    )

    if (configuration.orientation != ORIENTATION_PORTRAIT) {
        cols = 5
        numPadSymbols = listOf(
            Symbol.Seven, Symbol.Eight, Symbol.Nine, Symbol.Divide, Symbol.ClearAll,
            Symbol.Four, Symbol.Five, Symbol.Six, Symbol.Multiply, Symbol.Clear,
            Symbol.One, Symbol.Two, Symbol.Three, Symbol.Subtract, Symbol.ToggleSign,
            Symbol.DecimalPoint, Symbol.Zero, Symbol.Backspace, Symbol.Add, Symbol.Evaluate,
        )
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        padding -> Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.End,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = calculatorViewModel.toString(),
                        fontSize = 20.sp,
                        modifier = Modifier
                            .horizontalScroll(expressionScrollState)
                    )
                }

                Text(
                    text = state.value.currNumber.toString(),
                    fontSize = 32.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(currNumberScrollState),
                    textAlign = TextAlign.Right,
                )
            }

            NumPad(
                cols = cols,
                symbols = numPadSymbols,
                onClick = calculatorViewModel::onClick,
            )
        }
    }
}