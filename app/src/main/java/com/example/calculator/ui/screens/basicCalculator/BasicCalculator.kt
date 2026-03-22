package com.example.calculator.ui.screens.basicCalculator

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.calculator.core.BasicCalculatorViewModel

@Composable
fun BasicCalculator(viewModel: BasicCalculatorViewModel = BasicCalculatorViewModel()) {
    val configuration = LocalConfiguration.current

    if (configuration.orientation == ORIENTATION_PORTRAIT) {
        BasicCalculatorPortrait(
            viewModel = viewModel,
        )
    }
}