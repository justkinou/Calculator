package com.example.calculator.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.Stack

data class CalculatorState(
    val numberStack: Stack<InputNumber> = Stack(),
    val operatorStack: Stack<Char> = Stack(),
    val currNumber: InputNumber? = null,
)

class CalculatorViewModel : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state: StateFlow<CalculatorState> = _state
    private var clearJob: Job? = null

    fun onClick(symbol: Symbol) {
        when (symbol) {
            Symbol.Clear -> {
                if (clearJob?.isActive == true) {
                    clearJob?.cancel()
                    clearAll()
                } else {
                    clearJob = viewModelScope.launch {
                        delay(200)
                        clearInput()
                    }
                }
            }
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
            Symbol.Power -> changeOperator('^')
            Symbol.Sines -> {
                _state.update { currState ->
                    val currNumber = currState.currNumber?.copy() ?: InputNumber()
                    currNumber.sin()
                    currState.copy(
                        currNumber = currNumber
                    )
                }
            }
            Symbol.Cosines -> {
                _state.update { currState ->
                    val currNumber = currState.currNumber?.copy() ?: InputNumber()
                    currNumber.cos()
                    currState.copy(
                        currNumber = currNumber
                    )
                }
            }
            Symbol.Tangent -> {
                _state.update { currState ->
                    val currNumber = currState.currNumber?.copy() ?: InputNumber()
                    currNumber.tan()
                    currState.copy(
                        currNumber = currNumber
                    )
                }
            }
            Symbol.NaturalLogarithm -> {
                _state.update { currState ->
                    val currNumber = currState.currNumber?.copy() ?: InputNumber()
                    currNumber.ln()
                    currState.copy(
                        currNumber = currNumber
                    )
                }
            }
            Symbol.CommonLogarithm -> {
                _state.update { currState ->
                    val currNumber = currState.currNumber?.copy() ?: InputNumber()
                    currNumber.log()
                    currState.copy(
                        currNumber = currNumber
                    )
                }
            }
            Symbol.Square -> {
                _state.update { currState ->
                    val currNumber = currState.currNumber?.copy() ?: InputNumber()
                    currNumber.pow2()
                    currState.copy(
                        currNumber = currNumber
                    )
                }
            }
            Symbol.SquareRoot -> {
                _state.update { currState ->
                    val currNumber = currState.currNumber?.copy() ?: InputNumber()
                    currNumber.sqrt()
                    currState.copy(
                        currNumber = currNumber
                    )
                }
            }
            Symbol.Percent -> {
                _state.update { currState ->
                    val currNumber = currState.currNumber?.copy() ?: InputNumber()
                    currNumber.percent()
                    currState.copy(
                        currNumber = currNumber
                    )
                }
            }
        }
    }

    fun clearAll() {
        _state.update { CalculatorState() }
    }

    private fun clearInput() {
        _state.update { currState ->
            val numberStack = copyStack(currState.numberStack)
            val operatorStack = copyStack(currState.operatorStack)
            var currNumber = currState.currNumber?.copy()

            if (currNumber == null) {
                if (operatorStack.size == numberStack.size) {
                    if (operatorStack.isNotEmpty()) {
                        operatorStack.pop()
                        currNumber = numberStack.pop()
                    }
                }
            } else {
                currNumber = null
            }

            currState.copy(
                numberStack = numberStack,
                operatorStack = operatorStack,
                currNumber = currNumber,
            )
        }
    }

    private fun backspace() {
        _state.update { currState ->
            val numberStack = copyStack(currState.numberStack)
            val operatorStack = copyStack(currState.operatorStack)
            var currNumber = currState.currNumber?.copy()

            if (currNumber == null) {
                if (operatorStack.size == numberStack.size) {
                    if (operatorStack.isNotEmpty()) {
                        operatorStack.pop()
                        currNumber = numberStack.pop()
                    }
                }
            } else if (currNumber.backspace()) {
                if (numberStack.isNotEmpty()) {
                    currNumber = numberStack.pop()
                    if (numberStack.size < operatorStack.size) {
                        operatorStack.pop()
                    }
                } else {
                    currNumber = null
                }
            }

            currState.copy(
                numberStack = numberStack,
                operatorStack = operatorStack,
                currNumber = currNumber,
            )
        }
    }

    private fun changeOperator(operator: Char) {
        _state.update { currState ->
            val numberStack = copyStack(currState.numberStack)
            val operatorStack = copyStack(currState.operatorStack)
            var currNumber = currState.currNumber?.copy()

            if (currNumber == null) {
                if (operatorStack.size == numberStack.size) {
                    if (operatorStack.isNotEmpty()) {
                        operatorStack.pop()
                    }
                }
            } else {
                numberStack.push(currNumber)
                currNumber = null
            }
            operatorStack.push(operator)

            currState.copy(
                numberStack = numberStack,
                operatorStack = operatorStack,
                currNumber = currNumber,
            )
        }
    }

    private fun evaluate() {
        _state.update { currState ->
            val expression = toString() + currState.currNumber.toString()
            Log.d("tmp", expression)
            val result = ExpressionBuilder(expression).build().evaluate()
            currState.copy(
                numberStack = Stack(),
                operatorStack = Stack(),
                currNumber = InputNumber(result.toString()),
            )
        }
    }

    private fun addDecimalPoint() {
        _state.update { currState ->
            val currNumber = currState.currNumber?.copy() ?: InputNumber()
            currNumber.addDecimalPoint()
            currState.copy(
                currNumber = currNumber
            )
        }
    }

    private fun toggleSign() {
        _state.update { currState ->
            val currNumber = currState.currNumber?.copy() ?: InputNumber()
            currNumber.toggleSign()
            currState.copy(
                currNumber = currNumber
            )
        }
    }

    private fun addDigit(d: Char) {
        _state.update { currState ->
            val currNumber = currState.currNumber?.copy() ?: InputNumber()
            currNumber.addDigit(d)
            currState.copy(
                currNumber = currNumber,
            )
        }
    }

    private fun <T> copyStack(original: Stack<T>): Stack<T> {
        val copy = Stack<T>()
        copy.addAll(original)
        return copy
    }

    override fun toString(): String {
        val numberStack = _state.value.numberStack
        val operatorStack = _state.value.operatorStack
        val sb = StringBuilder()
        for (i in 0 until numberStack.size) {
            sb.append(numberStack[i].toString())
            if (i < operatorStack.size) {
                sb.append(" ${operatorStack[i]} ")
            }
        }
        return sb.toString()
    }
}