package com.example.calculator.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Calculator",
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )

            Text("Author: Andrii Bialkovskyi", fontSize = 14.sp)

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedButton(
                    onClick = { navController.navigate(Routes.BASIC_CALCULATOR) },
                    modifier = Modifier
                        .defaultMinSize(minWidth = 300.dp)
                ) {
                    Text("Basic calculator")
                }

                OutlinedButton(
                    onClick = {  },
                    modifier = Modifier
                        .defaultMinSize(minWidth = 300.dp)
                ) {
                    Text("Advanced calculator")
                }

                OutlinedButton(
                    onClick = { navController.navigate(Routes.ABOUT) },
                    modifier = Modifier
                        .defaultMinSize(minWidth = 300.dp)
                ) {
                    Text("About")
                }

                Button(
                    onClick = onExit,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 300.dp)
                ) {
                    Text("Exit")
                }
            }
        }
    }
}