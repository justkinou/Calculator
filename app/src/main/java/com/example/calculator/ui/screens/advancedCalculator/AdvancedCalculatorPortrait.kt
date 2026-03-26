package com.example.calculator.ui.screens.basicCalculator

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
fun AdvancedCalculatorPortrait(
    calculatorViewModel: CalculatorViewModel = viewModel<CalculatorViewModel>()
) {
    val state = calculatorViewModel.state.collectAsState()
    val currNumberScrollState = rememberScrollState()
    val expressionScrollState = rememberScrollState()

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
                        fontSize = 20.sp,
                        modifier = Modifier
                            .horizontalScroll(expressionScrollState),
                        color = QuickSilver,
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    CalculatorButton(
                        backgroundColor = QuickSilver,
                        onClick = { calculatorViewModel.onClick(Symbol.ClearAll) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.ClearAll.getSymbol(), color = Black)
                    }

                    CalculatorButton(
                        backgroundColor = QuickSilver,
                        onClick = { calculatorViewModel.onClick(Symbol.Clear) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Clear.getSymbol(), color = Black)
                    }

                    CalculatorButton(
                        backgroundColor = QuickSilver,
                        onClick = { calculatorViewModel.onClick(Symbol.ToggleSign) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.ToggleSign.getSymbol(), color = Black)
                    }

                    CalculatorButton(
                        backgroundColor = QuickSilver,
                        onClick = { calculatorViewModel.onClick(Symbol.CommonLogarithm) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.CommonLogarithm.getSymbol(), color = Black)
                    }

                    CalculatorButton(
                        backgroundColor = QuickSilver,
                        onClick = { calculatorViewModel.onClick(Symbol.NaturalLogarithm) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.NaturalLogarithm.getSymbol(), color = Black)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Sines) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Sines.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Cosines) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Cosines.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Tangent) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Tangent.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Percent) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Percent.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = VitaminC,
                        onClick = { calculatorViewModel.onClick(Symbol.Divide) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Divide.getSymbol(), color = White)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Seven) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Seven.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Eight) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Eight.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Nine) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Nine.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Power) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Power.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = VitaminC,
                        onClick = { calculatorViewModel.onClick(Symbol.Multiply) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Multiply.getSymbol(), color = White)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Four) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Four.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Five) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Five.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Six) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Six.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Square) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Square.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = VitaminC,
                        onClick = { calculatorViewModel.onClick(Symbol.Subtract) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Subtract.getSymbol(), color = White)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.One) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.One.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Two) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Two.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Three) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Three.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.SquareRoot) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.SquareRoot.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = VitaminC,
                        onClick = { calculatorViewModel.onClick(Symbol.Add) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Add.getSymbol(), color = White)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.DecimalPoint) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.DecimalPoint.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Zero) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Zero.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = Carbon,
                        onClick = { calculatorViewModel.onClick(Symbol.Backspace) },
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(Symbol.Backspace.getSymbol(), color = White)
                    }

                    CalculatorButton(
                        backgroundColor = VitaminC,
                        onClick = { calculatorViewModel.onClick(Symbol.Evaluate) },
                        modifier = Modifier.weight(2f).fillMaxHeight()
                    ) {
                        Text(Symbol.Evaluate.getSymbol(), color = White)
                    }
                }
            }
        }
    }
}