package com.example.calculator.core

import java.math.BigDecimal

class InputNumber {
    private var repr = StringBuilder("0")
    private var hasDecimal = false
    private var putDecimal = false
    private var isPositive = true

    private fun isEmpty(): Boolean {
        return repr.length == 1 && repr[0] == '0'
    }

    fun addDigit(d: Char) {
        if (isEmpty()) {
            repr.deleteAt(0)
            repr.append(d)
            return
        }
        if (hasDecimal && !putDecimal) {
            repr.deleteAt(repr.length - 1)
            repr.append(d)
            return
        }
        repr.append(d)
    }

    fun addDecimal() {
        if (hasDecimal) {
            return
        }
        hasDecimal = true
        repr.append(".0")
    }

    fun removeDigit() {
        if (repr.length == 1) {
            isPositive = true
            repr.deleteAt(0)
            repr.append("0")
            return
        }
        if (repr[repr.length - 2] == '.') {
            putDecimal = false
            hasDecimal = false
            repr.deleteRange(repr.length - 2, repr.length)
            return
        }
        repr.deleteAt(repr.length - 1)
    }

    fun toggleSign() {
        if (isEmpty()) {
            isPositive = true
            return
        }
        isPositive = !isPositive
    }

    override fun toString(): String {
        val strRepr = repr.toString()
        if (!isPositive) {
            return "-$strRepr"
        }
        return strRepr
    }

    fun toBigDecimal(): BigDecimal {
        var strRepr = repr.toString()
        if (!isPositive) {
            strRepr = "-$strRepr"
        }
        return BigDecimal(strRepr)
    }
}