package com.example.calculator.core

import java.math.BigDecimal

class InputNumber {
    private var number = StringBuilder("0")

    private fun isBlank(): Boolean {
        return number.length == 1 && number[0] == '0'
    }

    private fun hasDecimalPoint(): Boolean {
        return number.contains('.');
    }

    fun addDigit(d: Char) {
        if (isBlank()) {
            number.deleteAt(0)
            number.append(d)
            return
        }
        number.append(d)
    }

    fun addDecimalPoint() {
        if (hasDecimalPoint()) {
            return
        }
        number.append(".")
    }

    fun backspace(): Boolean {
        if (isBlank()) {
            return true
        }
        number.deleteAt(number.length - 1)
        if (number.isEmpty()) {
            number.append('0')
        }
        return false
    }

    fun toggleSign() {
        if (isBlank()) {
            return
        }
        if (number[0] == '-') {
            number.deleteAt(0)
        } else {
            number.insert(0, '-')
        }
    }

    fun toBigDecimal(): BigDecimal {
        return BigDecimal(number.toString())
    }
}