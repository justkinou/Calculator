package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculator.ui.screens.about.AboutScreen
import com.example.calculator.ui.screens.basicCalculator.BasicCalculator
import com.example.calculator.ui.screens.home.HomeScreen
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.util.Routes
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.HOME,
                ) {
                    composable(Routes.HOME) {
                        HomeScreen(
                            navController = navController,
                            onExit = {
                                finishAffinity()
                                exitProcess(0)
                            }
                        )
                    }

                    composable(Routes.ABOUT) {
                        AboutScreen(navController)
                    }

                    composable(Routes.BASIC_CALCULATOR) {
                        BasicCalculator()
                    }
                }
            }
        }
    }
}

