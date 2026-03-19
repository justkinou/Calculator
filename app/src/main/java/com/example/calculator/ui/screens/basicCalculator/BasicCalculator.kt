package com.example.calculator.ui.screens.basicCalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.calculator.core.BasicCalculatorViewModel
import com.example.calculator.ui.composables.CalculatorButton
import com.example.calculator.ui.theme.Black
import com.example.calculator.ui.theme.Carbon
import com.example.calculator.ui.theme.QuickSilver
import com.example.calculator.ui.theme.VitaminC

@Composable
fun BasicCalculator(viewModel: BasicCalculatorViewModel = BasicCalculatorViewModel()) {
    val expression = viewModel.expressionText.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) {
        padding -> Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(expression.value, fontSize = 20.sp)
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    CalculatorButton(
                        symbol = "AC",
                        symbolColor = Black,
                        backgroundColor = QuickSilver,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )

                    CalculatorButton(
                        symbol = "C",
                        symbolColor = Black,
                        backgroundColor = QuickSilver,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )

                    CalculatorButton(
                        symbol = "±",
                        symbolColor = Black,
                        backgroundColor = QuickSilver,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )

                    CalculatorButton(
                        symbol = "/",
                        backgroundColor = VitaminC,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    CalculatorButton(
                        symbol = "7",
                        backgroundColor = Carbon,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )

                    CalculatorButton(
                        symbol = "8",
                        backgroundColor = Carbon,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )

                    CalculatorButton(
                        symbol = "9",
                        backgroundColor = Carbon,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )

                    CalculatorButton(
                        symbol = "*",
                        backgroundColor = VitaminC,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    CalculatorButton(
                        symbol = "4",
                        backgroundColor = Carbon,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )

                    CalculatorButton(
                        symbol = "5",
                        backgroundColor = Carbon,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )

                    CalculatorButton(
                        symbol = "6",
                        backgroundColor = Carbon,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )

                    CalculatorButton(
                        symbol = "-",
                        backgroundColor = VitaminC,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    CalculatorButton(
                        symbol = "1",
                        backgroundColor = Carbon,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )

                    CalculatorButton(
                        symbol = "2",
                        backgroundColor = Carbon,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )

                    CalculatorButton(
                        symbol = "3",
                        backgroundColor = Carbon,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )

                    CalculatorButton(
                        symbol = "+",
                        backgroundColor = VitaminC,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )
                }

                Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
                    CalculatorButton(
                        symbol = "0",
                        backgroundColor = Carbon,
                        modifier = Modifier.weight(2f).fillMaxHeight(),
                        onClick = { },
                    )


                    CalculatorButton(
                        symbol = ".",
                        backgroundColor = Carbon,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )

                    CalculatorButton(
                        symbol = "=",
                        backgroundColor = VitaminC,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { },
                    )
                }
            }
        }
    }
}