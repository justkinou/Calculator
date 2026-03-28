package com.example.calculator.core

import android.util.Log
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.log10

class InputNumber {
    private var number = StringBuilder("0")

    constructor() { }

    constructor(s: String) {
        number = StringBuilder(s)
    }

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
        Log.d("test", "$number")
        number.deleteAt(number.length - 1)
        Log.d("test", "$number")
        if (number.isEmpty()) {
            number.append('0')
        }
        return false
    }

    fun toggleSign() {
        if (number[0] == '-') {
            number.deleteAt(0)
        } else {
            number.insert(0, '-')
        }
    }

    override fun toString(): String {
        return number.toString()
    }

    fun toBigDecimal(): BigDecimal {
        return BigDecimal(toString())
    }

    fun copy(): InputNumber {
        return InputNumber(toString())
    }

    fun sin() {
        val raw = BigDecimal(kotlin.math.sin(toBigDecimal().toDouble()))
        val scaled = raw.setScale(8, RoundingMode.HALF_UP)
        number = StringBuilder(scaled.stripTrailingZeros().toPlainString())
    }

    fun cos() {
        val raw = BigDecimal(kotlin.math.cos(toBigDecimal().toDouble()))
        val scaled = raw.setScale(8, RoundingMode.HALF_UP)
        number = StringBuilder(scaled.stripTrailingZeros().toPlainString())
    }

    fun tan() {
        val radians = toBigDecimal().toDouble()
        if (kotlin.math.cos(radians) == 0.0) {
            throw ArithmeticException("tan undefined at this value")
        }
        val raw = BigDecimal(kotlin.math.tan(radians))
        val scaled = raw.setScale(8, RoundingMode.HALF_UP)
        number = StringBuilder(scaled.stripTrailingZeros().toPlainString())
    }

    fun log() {
        if (toBigDecimal() <= BigDecimal.ZERO) {
            throw ArithmeticException("log undefined for x ≤ 0")
        }
        val raw = BigDecimal(log10(toBigDecimal().toDouble()))
        val scaled = raw.setScale(8, RoundingMode.HALF_UP)
        number = StringBuilder(scaled.stripTrailingZeros().toPlainString())
    }

    fun ln() {
        if (toBigDecimal() <= BigDecimal.ZERO) {
            throw ArithmeticException("ln undefined for x ≤ 0")
        }
        val raw = BigDecimal(kotlin.math.ln(toBigDecimal().toDouble()))
        val scaled = raw.setScale(8, RoundingMode.HALF_UP)
        number = StringBuilder(scaled.stripTrailingZeros().toPlainString())
    }

    fun sqrt() {
        if (toBigDecimal() < BigDecimal.ZERO) {
            throw ArithmeticException("sqrt undefined for x < 0")
        }
        val raw = BigDecimal(kotlin.math.sqrt(toBigDecimal().toDouble()))
        val scaled = raw.setScale(8, RoundingMode.HALF_UP)
        number = StringBuilder(scaled.stripTrailingZeros().toPlainString())
    }

    fun pow2() {
        val raw = toBigDecimal().pow(2)
        val scaled = raw.setScale(8, RoundingMode.HALF_UP)
        number = StringBuilder(scaled.stripTrailingZeros().toPlainString())
    }

    fun percent() {
        val raw = toBigDecimal().divide(BigDecimal(100))
        number = StringBuilder(raw.stripTrailingZeros().toPlainString())
    }
}