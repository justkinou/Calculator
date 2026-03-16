package com.example.calculator.ui.screens.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calculator.ui.composables.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navController) },
    ) {
        padding -> Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(8.dp),
        ) {
            Text("""
                This is a calculator app for my Mobile Development course.
                The basic calculator allows for simple operations, such as addition, subtraction, multiplication, division, clearing the input etc.
                The advanced calculator allows for more complex operations, including sqrt, percent, logarithm, trigonometric functions etc.
                The app is build by Andrii Bialkovskyi using Kotlin and Jetpack Compose.
            """.trimIndent())
        }
    }
}