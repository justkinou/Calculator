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

    fun onClick(symbol: Symbol) {
        when (symbol) {
            Symbol.Clear -> clearInput()
            Symbol.ClearAll -> clearAll()
            Symbol.Backspace -> backspace()
            Symbol.Add -> changeOperator('+')
            Symbol.Subtract -> changeOperator('-')
            Symbol.Divide -> changeOperator('/')
            Symbol.Multiply -> changeOperator('*')
            Symbol.Evaluate -> evaluate()
            Symbol.DecimalPoint -> TODO()
            Symbol.ToggleSign -> TODO()
            Symbol.Zero -> TODO()
            Symbol.One -> TODO()
            Symbol.Two -> TODO()
            Symbol.Three -> TODO()
            Symbol.Four -> TODO()
            Symbol.Five -> TODO()
            Symbol.Six -> TODO()
            Symbol.Seven -> TODO()
            Symbol.Eight -> TODO()
            Symbol.Nine -> TODO()
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