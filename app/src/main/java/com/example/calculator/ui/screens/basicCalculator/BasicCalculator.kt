package com.example.calculator.ui.screens.basicCalculator

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.core.BasicCalculatorViewModel
import com.example.calculator.core.Symbol
import com.example.calculator.ui.composables.NumPad

@Composable
fun BasicCalculator(viewModel: BasicCalculatorViewModel = BasicCalculatorViewModel()) {
    val state = viewModel.state.collectAsState()
    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    var cols = 4
    if (configuration.orientation != ORIENTATION_PORTRAIT) {
        cols = 5
    }

    val numPadSymbols = listOf(
        Symbol.ClearAll, Symbol.Clear, Symbol.ToggleSign, Symbol.Divide,
        Symbol.Seven, Symbol.Eight, Symbol.Nine, Symbol.Multiply,
        Symbol.Four, Symbol.Five, Symbol.Six, Symbol.Subtract,
        Symbol.One, Symbol.Two, Symbol.Three, Symbol.Add,
        Symbol.DecimalPoint, Symbol.Zero, Symbol.Backspace, Symbol.Evaluate,
    )

    Scaffold(modifier = Modifier.fillMaxSize()) {
        padding -> Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = state.value.prevNumber?.toString() ?: "",
                    fontSize = 64.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollState),
                    textAlign = TextAlign.Right,
                )

                Text(
                    text = state.value.operator?.toString() ?: "",
                    fontSize = 64.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollState),
                    textAlign = TextAlign.Right,
                )

                Text(
                    text = state.value.currNumber?.toString() ?: "0",
                    fontSize = 64.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollState),
                    textAlign = TextAlign.Right,
                )
            }

            NumPad(
                cols = cols,
                symbols = numPadSymbols,
                onClick = viewModel::onClick,
            )
        }
    }
}