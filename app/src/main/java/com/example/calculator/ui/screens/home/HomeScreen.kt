package com.example.calculator.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
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

            Text("Author: Andrii Bialkovskyi", fontSize = 24.sp)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate(Routes.BASIC_CALCULATOR) },
                ) {
                    Text("Basic Calculator", fontSize = 24.sp)
                }

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate(Routes.BASIC_CALCULATOR) },
                ) {
                    Text("Advanced Calculator", fontSize = 24.sp)
                }

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate(Routes.ABOUT) },
                ) {
                    Text("About", fontSize = 24.sp)
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onExit,
                ) {
                    Text("Exit", fontSize = 24.sp)
                }
            }
        }
    }
}