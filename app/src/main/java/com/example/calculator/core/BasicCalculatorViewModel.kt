package com.example.calculator.core

import androidx.lifecycle.ViewModel
import com.notkamui.keval.Keval
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BasicCalculatorViewModel : ViewModel() {
    private val expression = MutableStateFlow("")
    val expressionText: StateFlow<String> = expression

    fun onClick(symbol: String) {
        when (symbol) {
            "AC", "C" -> {
                expression.value = ""
            }
            "=" -> {
                expression.value = evaluate()
            }
            "±" -> {
                expression.value = toggleSign()
            }
            else -> {
                if (!"0123456789.".contains(symbol)) {
                    expression.value += ' '
                }
                if ("-+/*".contains(expression.value.getOrElse(expression.value.length - 1, { ' ' }))) {
                    expression.value += ' '
                }
                expression.value += symbol
            }
        }
    }

    private fun toggleSign(): String {
        val expr = expression.value
        var i = expr.length - 1
        while (i >= 0 && expr[i] < '0' && expr[i] > '9') {
            --i
        }
        while (i >= 0 && expr[i] >= '0' && expr[i] <= '9') {
            --i;
        }
        if (i == -1) {
            if ("01234567890".contains(expr.getOrElse(0, { ' ' }))) {
                return "-$expr"
            }
            return expr
        }
        if (expr[i] == '-') {
            return expr.substring(0, i) + expr.substring(i + 1)
        }
        return expr.substring(0, i + 1) + "-" + expr.substring(i + 1)
    }

    private fun evaluate(): String {
        return Keval.eval(expression.value).toString()
    }
}