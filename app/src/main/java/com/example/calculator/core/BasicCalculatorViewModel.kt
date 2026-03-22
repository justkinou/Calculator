package com.example.calculator.core

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.math.RoundingMode

data class CalculatorState(
    val prevNumber: InputNumber? = null,
    val currNumber: InputNumber? = null,
    val operator: Char? = null,
)

class BasicCalculatorViewModel : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state: StateFlow<CalculatorState> = _state

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
            Symbol.DecimalPoint -> addDecimalPoint()
            Symbol.ToggleSign -> toggleSign()
            Symbol.Zero, Symbol.One, Symbol.Two, Symbol.Three,
            Symbol.Four, Symbol.Five, Symbol.Six, Symbol.Seven,
            Symbol.Eight, Symbol.Nine -> addDigit(symbol.getSymbol()[0])
        }
    }

    fun clearAll() {
        _state.update { CalculatorState() }
    }

    private fun clearInput() {
        _state.update { currState ->
            var currNumber = currState.currNumber?.copy()
            var prevNumber = currState.prevNumber?.copy()
            var operator = currState.operator

            if (currNumber != null && prevNumber != null) {
                currNumber = prevNumber
                prevNumber = null
            } else if (operator != null) {
                operator = null
            } else if (currNumber != null) {
                currNumber = null
            }

            currState.copy(
                prevNumber = prevNumber,
                currNumber = currNumber,
                operator = operator,
            )
        }
    }

    private fun backspace() {
        _state.update { currState ->
            var currNumber = currState.currNumber?.copy()
            var prevNumber = currState.prevNumber?.copy()
            var operator = currState.operator

            if (currNumber != null) {
                if (currNumber.backspace()) {
                    currNumber = prevNumber
                    prevNumber = null
                    operator = null
                }
            } else if (operator != null) {
                operator = null
                currNumber = prevNumber
                prevNumber = null
            }

            currState.copy(
                prevNumber = prevNumber,
                currNumber = currNumber,
                operator = operator,
            )
        }
    }

    private fun changeOperator(operator: Char) {
        _state.update { currState ->
            var currNumber = currState.currNumber?.copy()
            if (currNumber == null) {
                currNumber = InputNumber()
            }
            currState.copy(operator = operator, currNumber = currNumber)
        }
    }

    private fun evaluate() {
        _state.update { currState ->
            var prevNumber = currState.prevNumber?.copy()
            var currNumber = currState.currNumber?.copy()
            var operator = currState.operator

            val left = prevNumber?.toBigDecimal() ?: BigDecimal.ZERO
            val right = currNumber?.toBigDecimal() ?: BigDecimal.ZERO
            val result = when (operator) {
                '+' -> left.add(right)
                '-' -> left.subtract(right)
                '*' -> left.multiply(right)
                '/' -> {
                    if (right.compareTo(BigDecimal(0)) == 0) {
                        throw IllegalArgumentException("Division by zero")
                    }
                    left.divide(right, 12, RoundingMode.HALF_UP).stripTrailingZeros()
                }
                else -> return
            }

            if (result != null) {
                prevNumber = null
                operator = null
                currNumber = InputNumber(result.toString())
            }

            currState.copy(
                prevNumber = prevNumber,
                currNumber = currNumber,
                operator = operator,
            )
        }
    }

    private fun addDecimalPoint() {
        _state.update { currState ->
            var currNumber = currState.currNumber?.copy() ?: InputNumber()
            currNumber.addDecimalPoint()

            currState.copy(
                currNumber = currNumber
            )
        }
    }

    private fun toggleSign() {
        _state.update { currState ->
            var currNumber = currState.currNumber?.copy()
            currNumber?.toggleSign()
            currState.copy(
                currNumber = currNumber
            )
        }
    }

    private fun addDigit(d: Char) {
        _state.update { currState ->
            var prevNumber = currState.prevNumber?.copy()
            var currNumber = currState.currNumber?.copy()
            var operator = currState.operator

            if (operator != null && prevNumber == null) {
                prevNumber = currNumber
                currNumber = InputNumber()
            }
            if (currNumber == null) {
                currNumber = InputNumber()
            }
            currNumber.addDigit(d)

            currState.copy(
                prevNumber = prevNumber,
                currNumber = currNumber,
                operator = operator,
            )
        }
    }
}