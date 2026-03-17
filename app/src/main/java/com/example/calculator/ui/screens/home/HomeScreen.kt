package com.example.calculator.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calculator.util.Routes

@Composable
fun HomeScreen(
        navController: NavController,
        onExit: () -> Unit
    ) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        padding -> Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Calculator",
                fontSize = 48.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )

            Text("Author: Andrii Bialkovskyi", fontSize = 16.sp)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                HomeButton(
                    text = "Basic Calculator",
                    onClick = { navController.navigate(Routes.BASIC_CALCULATOR) },
                    modifier = Modifier.fillMaxWidth(),
                )

                HomeButton(
                    text = "Advanced Calculator",
                    onClick = {  },
                    modifier = Modifier.fillMaxWidth(),
                )

                HomeButton(
                    text = "About",
                    onClick = { navController.navigate(Routes.ABOUT) },
                    modifier = Modifier.fillMaxWidth(),
                )

                Button(
                    onClick = onExit,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Exit", fontSize = 16.sp)
                }
            }
        }
    }
}