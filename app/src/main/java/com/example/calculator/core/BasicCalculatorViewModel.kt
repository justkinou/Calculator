package com.example.calculator.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal

private data class CalculatorState(
    var prevNumber: InputNumber? = null,
    var currNumber: InputNumber? = null,
    var operator: Char? = null,
)

class BasicCalculatorViewModel : ViewModel() {
    private val state = MutableStateFlow(CalculatorState())

    fun onClick(symbol: BasicSymbol) {
        when (symbol) {
            BasicSymbol.Clear -> clearInput()
            BasicSymbol.ClearAll -> clearAll()
            BasicSymbol.Backspace -> backspace()
            BasicSymbol.Add -> changeOperator('+')
            BasicSymbol.Subtract -> changeOperator('-')
            BasicSymbol.Divide -> changeOperator('/')
            BasicSymbol.Multiply -> changeOperator('*')
            BasicSymbol.Evaluate -> evaluate()
            BasicSymbol.DecimalPoint -> TODO()
            BasicSymbol.ToggleSign -> TODO()
            BasicSymbol.Zero -> TODO()
            BasicSymbol.One -> TODO()
            BasicSymbol.Two -> TODO()
            BasicSymbol.Three -> TODO()
            BasicSymbol.Four -> TODO()
            BasicSymbol.Five -> TODO()
            BasicSymbol.Six -> TODO()
            BasicSymbol.Seven -> TODO()
            BasicSymbol.Eight -> TODO()
            BasicSymbol.Nine -> TODO()
        }
    }

    fun clearAll() {
        state.update { CalculatorState() }
    }

    private fun clearInput() {
        state.update({ currState ->
            if (currState.currNumber != null) {
                currState.currNumber = currState.prevNumber
                currState.prevNumber = null
            } else if (currState.operator != null) {
                currState.operator = null
                currState.currNumber = currState.prevNumber
                currState.prevNumber = null
            }
            currState.copy()
        })
    }

    private fun backspace() {
        state.update({ currState ->
            if (currState.currNumber != null) {
                if (currState.currNumber?.backspace() == true) {
                    currState.currNumber = currState.prevNumber
                    currState.prevNumber = null
                }
            } else if (currState.operator != null) {
                currState.operator = null
            }
            currState.copy()
        })
    }

    private fun changeOperator(operator: Char) {
        state.update { currState ->
            currState.operator = operator
            if (currState.prevNumber == null) {
                if (currState.currNumber == null) {
                    currState.prevNumber = InputNumber()
                } else {
                    currState.prevNumber = currState.currNumber
                    currState.currNumber = null
                }
            }
            currState.copy()
        }
    }

    private fun evaluate() {
        state.update { currState ->
            var left = currState.prevNumber?.toBigDecimal()
            if (left == null) {
                left = BigDecimal(0)
            }
            var right = currState.currNumber?.toBigDecimal()
            if (right == null) {
                right = BigDecimal(0)
            }
            val result = when (currState.operator) {
                '+' -> left.add(right)
                '-' -> left.subtract(right)
                '*' -> left.multiply(right)
                '/' -> left.divide(right)
                else -> null
            }
            if (result != null) {
                currState.prevNumber = null
                currState.operator = null
                currState.currNumber = InputNumber(result.toString())
            }
            currState.copy()
        }
    }

    private fun addDecimalPoint() {
        state.update { currState ->
            currState.currNumber?.addDecimalPoint()
            currState.copy()
        }
    }
}