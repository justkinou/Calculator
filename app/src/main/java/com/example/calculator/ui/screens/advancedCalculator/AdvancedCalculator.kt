package com.example.calculator.ui.screens.advancedCalculator

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.core.CalculatorViewModel
import com.example.calculator.core.Symbol
import com.example.calculator.ui.composables.NumPad
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
    } else if (i == 0) {
        res[0] = QuickSilver
        res[1] = Black
    }
    return res
}


@Composable
fun AdvancedCalculator(
    calculatorViewModel: CalculatorViewModel = viewModel<CalculatorViewModel>()
) {
    val configuration = LocalConfiguration.current

    val expression = calculatorViewModel.expressionText.collectAsState()
    val expressionScrollState = rememberScrollState()

    LaunchedEffect(expression.value) {
        expressionScrollState.animateScrollTo(expressionScrollState.maxValue)
    }

    var getColors = ::getColorsPortrait
    if (configuration.orientation != ORIENTATION_PORTRAIT) {
        getColors = ::getColorsLandscape
    }

    var cols = 4
    var numPadSymbols = listOf(
        listOf(Symbol.ClearAll, Symbol.Clear, Symbol.ToggleSign, Symbol.Divide),
        listOf(Symbol.Seven, Symbol.Eight, Symbol.Nine, Symbol.Multiply),
        listOf(Symbol.Four, Symbol.Five, Symbol.Six, Symbol.Subtract),
        listOf(Symbol.One, Symbol.Two, Symbol.Three, Symbol.Add),
        listOf(Symbol.DecimalPoint, Symbol.Zero, Symbol.Backspace, Symbol.Evaluate),
    )

    if (configuration.orientation != ORIENTATION_PORTRAIT) {
        cols = 5
        numPadSymbols = listOf(
            listOf(Symbol.Seven, Symbol.Eight, Symbol.Nine, Symbol.Divide, Symbol.ClearAll),
            listOf(Symbol.Four, Symbol.Five, Symbol.Six, Symbol.Multiply, Symbol.Clear),
            listOf(Symbol.One, Symbol.Two, Symbol.Three, Symbol.Subtract, Symbol.ToggleSign),
            listOf(Symbol.DecimalPoint, Symbol.Zero, Symbol.Backspace, Symbol.Add, Symbol.Evaluate),
        )
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        padding -> Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(expressionScrollState)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                Text(
                    text = expression.value,
                    fontSize = if (configuration.orientation == ORIENTATION_PORTRAIT) 32.sp else 24.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Carbon,
                    textAlign = TextAlign.Right,
                )
            }

            NumPad(
                cols = cols,
                symbols = numPadSymbols,
                onClick = calculatorViewModel::onClick,
                modifier = Modifier
                    .weight(6f),
                getColors = getColors,
            )
        }
    }
}