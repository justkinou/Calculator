package com.example.calculator.core

enum class Symbol(private val symbol: String) {
    Clear("C"),
    ClearAll("AC"),
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
    SquareRoot("√"),
    Square("^2"),
    Power("^");

    fun getSymbol(): String {
        return symbol
    }
}