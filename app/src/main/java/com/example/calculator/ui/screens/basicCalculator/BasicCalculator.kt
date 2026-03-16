package com.example.calculator.ui.screens.basicCalculator

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calculator.ui.composables.CalculatorButton
import com.example.calculator.ui.composables.TopBar
import com.example.calculator.ui.theme.Black
import com.example.calculator.ui.theme.Carbon
import com.example.calculator.ui.theme.QuickSilver
import com.example.calculator.ui.theme.VitaminC

@Composable
fun BasicCalculator(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navController) }
    ) {
        padding -> Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Bottom),
        ) {
        Row(modifier = Modifier.fillMaxWidth()) {
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
                    symbol = "%",
                    symbolColor = Black,
                    backgroundColor = QuickSilver,
                    modifier = Modifier.weight(1f).aspectRatio(1f),
                    onClick = { },
                )

                CalculatorButton(
                    symbol = "÷",
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
                    symbol = "×",
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

            Row(modifier = Modifier.height(IntrinsicSize.Min)) {
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