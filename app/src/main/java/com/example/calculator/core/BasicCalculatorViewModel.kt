package com.example.calculator.core

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal

data class CalculatorState(
    var prevNumber: InputNumber? = null,
    var currNumber: InputNumber? = null,
    var operator: Char? = null,
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
        _state.update({ currState ->
            val nextState = currState.copy()

            if (nextState.currNumber != null) {
                nextState.currNumber = nextState.prevNumber
                nextState.prevNumber = null
            } else if (nextState.operator != null) {
                nextState.operator = null
                nextState.currNumber = nextState.prevNumber
                nextState.prevNumber = null
            }

            nextState
        })
    }

    private fun backspace() {
        _state.update({ currState ->
            val nextState = currState.copy()

            if (nextState.currNumber != null) {
                if (nextState.currNumber?.backspace() == true) {
                    nextState.currNumber = nextState.prevNumber
                    nextState.prevNumber = null
                }
            } else if (nextState.operator != null) {
                nextState.operator = null
                nextState.currNumber = nextState.prevNumber
                nextState.prevNumber = null
            }

            nextState
        })
    }

    private fun changeOperator(operator: Char) {
        _state.update { currState ->
            currState.copy(operator = operator)
        }
    }

    private fun evaluate() {
        _state.update { currState ->
            val nextState = currState.copy()

            var left = nextState.prevNumber?.toBigDecimal()
            if (left == null) {
                left = BigDecimal(0)
            }
            var right = nextState.currNumber?.toBigDecimal()
            if (right == null) {
                right = BigDecimal(0)
            }
            val result = when (nextState.operator) {
                '+' -> left.add(right)
                '-' -> left.subtract(right)
                '*' -> left.multiply(right)
                '/' -> left.divide(right)
                else -> null
            }
            if (result != null) {
                nextState.prevNumber = null
                nextState.operator = null
                nextState.currNumber = InputNumber(result.toString())
            }

            nextState
        }
    }

    private fun addDecimalPoint() {
        _state.update { currState ->
            val nextState = currState.copy()
            if (nextState.currNumber == null) {
                nextState.currNumber = InputNumber()
            }
            nextState.currNumber?.addDecimalPoint()
            nextState
        }
    }

    private fun toggleSign() {
        _state.update { currState ->
            val nextState = currState.copy()
            nextState.currNumber?.toggleSign()
            nextState
        }
    }

    private fun addDigit(d: Char) {
        _state.update { currState ->
            val nextState = currState.copy()

            if (nextState.operator != null && nextState.prevNumber == null) {
                nextState.prevNumber = nextState.currNumber
                nextState.currNumber = InputNumber()
            }
            if (nextState.currNumber == null) {
                nextState.currNumber = InputNumber()
            }
            nextState.currNumber?.addDigit(d)

            Log.d("D", nextState.toString())

            nextState
        }
    }
}