package com.example.calculator.core

enum class Symbol(private val symbol: String) {
    Clear("C"),
    ClearAll("AC"),
    Braces("()"),
    Backspace("←"),
    Add("+"),
    Subtract("-"),
    Divide("/"),
    Multiply("*"),
    Evaluate("="),
    DecimalPoint("."),
    ToggleSign("±"),
    Zero("0"),
    One("1"),
    Two("2"),
    Three("3"),
    Four("4"),
    Five("5"),
    Six("6"),
    Seven("7"),
    Eight("8"),
    Nine("9"),
    Sines("sin"),
    Cosines("cos"),
    Tangent("tan"),
    CommonLogarithm("log"),
    NaturalLogarithm("ln"),
    Percent("%"),
    SquareRoot("sqrt"),
    Square("^2"),
    Power("^");
    fun getSymbol(): String {
        return symbol
    }
}

val operators = listOf(
    Symbol.Add,
    Symbol.Subtract,
    Symbol.Divide,
    Symbol.Multiply,
)

val digits = listOf(
    Symbol.Zero,
    Symbol.One,
    Symbol.Two,
    Symbol.Three,
    Symbol.Four,
    Symbol.Five,
    Symbol.Six,
    Symbol.Seven,
    Symbol.Eight,
    Symbol.Nine,
)

val functions = listOf(
    Symbol.Sines, Symbol.Cosines, Symbol.Tangent,
    Symbol.CommonLogarithm, Symbol.NaturalLogarithm, Symbol.SquareRoot
)