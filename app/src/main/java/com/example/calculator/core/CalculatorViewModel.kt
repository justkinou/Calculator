package com.example.calculator.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigDecimal
import java.math.RoundingMode

open class CalculatorViewModel : ViewModel() {
    private val expression = MutableStateFlow("")
    val expressionText: StateFlow<String> = expression

    fun onClick(symbol: Symbol) {
        when (symbol) {
            Symbol.ClearAll -> clearAll()
            Symbol.Clear -> {
                expression.value = clear()
            }
            Symbol.Evaluate -> {
                expression.value = evaluate()
            }
            Symbol.ToggleSign -> {
                expression.value = toggleSign()
            }
            Symbol.Backspace -> {
                expression.value = backspace()
            }
            else -> {
                if (functions.contains(symbol)) {
                    val c = expression.value.getOrElse(expression.value.length - 1, { ' ' })
                    if ("01234567890.)".contains(c)) {
                        expression.value += " * "
                    }
                    expression.value += symbol.getSymbol() + "("
                    return
                }
                if (digits.contains(symbol) || symbol == Symbol.DecimalPoint) {
                    val c = expression.value.getOrElse(expression.value.length - 1, { ' ' })
                    if ("+-*/".contains(c)) {
                        expression.value += ' '
                    }
                } else if (operators.contains(symbol)) {
                    val c = expression.value.getOrElse(expression.value.length - 1, { ' ' })
                    if ("01234567890".contains(c)) {
                        expression.value += ' '
                    }
                }
                expression.value += symbol.getSymbol()
            }
        }
    }

    private fun clearAll() {
        expression.value = ""
    }

    private fun clear(): String {
        val expr = expression.value
        var i = expr.length - 1
        while (i >= 0 && expr[i] == ' ') {
            --i
        }
        while (i >= 0 && expr[i] != ' ') {
            --i;
        }
        while (i >= 0 && expr[i] == ' ') {
            --i
        }
        return expr.substring(0, i + 1)
    }

    private fun toggleSign(): String {
        val expr = expression.value
        var i = expr.length - 1
        while (i >= 0 && !"01234567890.".contains(expr[i])) {
            --i
        }
        while (i >= 0 && "01234567890.".contains(expr[i])) {
            --i;
        }
        if (i == -1) {
            if ("01234567890.".contains(expr.getOrElse(0) { ' ' })) {
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
        return ExpressionBuilder(expression.value).build().evaluate().toString()
    }

    private fun backspace(): String {
        val expr = expression.value
        if (expr.isEmpty()) {
            return ""
        }
        var i = expr.length - 2
        while (i >= 0 && expr[i] == ' ') {
            --i
        }
        return expr.substring(0, i + 1)
    }
}