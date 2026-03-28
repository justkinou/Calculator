package com.example.calculator.ui.screens.advancedCalculator

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.platform.LocalWindowInfo
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
fun AdvancedCalculator(
    calculatorViewModel: CalculatorViewModel = viewModel<CalculatorViewModel>()
) {
    val state = calculatorViewModel.state.collectAsState()
    val currNumberScrollState = rememberScrollState()
    val expressionScrollState = rememberScrollState()
    val orientation = LocalConfiguration.current.orientation
    val scale = minOf(
        LocalWindowInfo.current.containerSize.width,
        LocalWindowInfo.current.containerSize.height,
    ) / 720f

    val expressionFontSize = (14 * scale).sp
    val numberFontSize = (24 * scale).sp
    val buttonFontSize = (14 * scale).sp

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
            Text(
                text = calculatorViewModel.toString(),
                fontSize = expressionFontSize,
                modifier = Modifier.horizontalScroll(expressionScrollState),
                color = QuickSilver,
            )

            Text(
                text = state.value.currNumber?.toString() ?: "0",
                fontSize = numberFontSize,
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(currNumberScrollState),
                textAlign = TextAlign.Right,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(if (orientation == ORIENTATION_PORTRAIT) 5f else 3f),
        ) {
            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                CalculatorButton(backgroundColor = QuickSilver, onClick = { calculatorViewModel.onClick(Symbol.ClearAll) }, label = Symbol.ClearAll.getSymbol(), textColor = Black, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = QuickSilver, onClick = { calculatorViewModel.onClick(Symbol.Clear) }, label = Symbol.Clear.getSymbol(), textColor = Black, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = QuickSilver, onClick = { calculatorViewModel.onClick(Symbol.ToggleSign) }, label = Symbol.ToggleSign.getSymbol(), textColor = Black, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = QuickSilver, onClick = { calculatorViewModel.onClick(Symbol.CommonLogarithm) }, label = Symbol.CommonLogarithm.getSymbol(), textColor = Black, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = QuickSilver, onClick = { calculatorViewModel.onClick(Symbol.NaturalLogarithm) }, label = Symbol.NaturalLogarithm.getSymbol(), textColor = Black, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
            }

            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Sines) }, label = Symbol.Sines.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Cosines) }, label = Symbol.Cosines.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Tangent) }, label = Symbol.Tangent.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Percent) }, label = Symbol.Percent.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = VitaminC, onClick = { calculatorViewModel.onClick(Symbol.Divide) }, label = Symbol.Divide.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
            }

            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Seven) }, label = Symbol.Seven.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Eight) }, label = Symbol.Eight.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Nine) }, label = Symbol.Nine.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Power) }, label = Symbol.Power.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = VitaminC, onClick = { calculatorViewModel.onClick(Symbol.Multiply) }, label = Symbol.Multiply.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
            }

            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Four) }, label = Symbol.Four.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Five) }, label = Symbol.Five.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Six) }, label = Symbol.Six.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Square) }, label = Symbol.Square.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = VitaminC, onClick = { calculatorViewModel.onClick(Symbol.Subtract) }, label = Symbol.Subtract.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
            }

            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.One) }, label = Symbol.One.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Two) }, label = Symbol.Two.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Three) }, label = Symbol.Three.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.SquareRoot) }, label = Symbol.SquareRoot.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = VitaminC, onClick = { calculatorViewModel.onClick(Symbol.Add) }, label = Symbol.Add.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
            }

            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.DecimalPoint) }, label = Symbol.DecimalPoint.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Zero) }, label = Symbol.Zero.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = Carbon, onClick = { calculatorViewModel.onClick(Symbol.Backspace) }, label = Symbol.Backspace.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(1f).fillMaxHeight())
                CalculatorButton(backgroundColor = VitaminC, onClick = { calculatorViewModel.onClick(Symbol.Evaluate) }, label = Symbol.Evaluate.getSymbol(), textColor = White, fontSize = buttonFontSize, modifier = Modifier.weight(2f).fillMaxHeight())
            }
        }
    }
    }
}