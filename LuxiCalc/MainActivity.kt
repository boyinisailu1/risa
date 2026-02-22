package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.calculator.ui.TextOnCAlTOP
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                CalculatorApp()
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    var expression by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var history by remember { mutableStateOf(listOf<String>()) }
    var showHistory by remember { mutableStateOf(false) }

    if (showHistory) {
        HistoryScreen(
            history = history,
            onClearHistory = { history = emptyList() },
            onBack = { showHistory = false }
        )
    } else {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TextOnCAlTOP() }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Bottom
            ) {
                DisplayArea(
                    expression = expression,
                    result = result
                )

                HorizontalDivider()

                CalculatorButtonGrid { label ->
                    when (label) {
                        "AC" -> {
                            expression = ""
                            result = ""
                        }

                        "⌫" -> {
                            if (expression.isNotEmpty()) {
                                expression = expression.dropLast(1)
                            }
                        }

                        "=" -> {
                            result = evaluateExpression(expression)
                            if (result != "Error") {
                                history = history + "$expression = $result"
                            }
                        }

                        "History" -> {
                            showHistory = true
                        }

                        else -> {
                            expression += label
                        }
                    }
                }
            }
        }
    }
}
