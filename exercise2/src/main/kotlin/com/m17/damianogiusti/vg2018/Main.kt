package com.m17.damianogiusti.vg2018

fun sum(a: Double, b: Double) = a + b
fun sub(a: Double, b: Double) = a - b
fun mul(a: Double, b: Double) = a * b
fun div(a: Double, b: Double) = a / b

typealias Operator = (Double, Double) -> Double

fun main(args: Array<String>) {
    println("Inserisci l'espressione RPN da valutare:")
    val expression = readLine() ?: error("Impossibile leggere da input!")

    require(expression.isNotEmpty()) { "L'espressione non può essere vuota!" }
    require(expression.count { it == '(' } == expression.count { it == ')' }) {
        "Il numero di parentesi aperte e chiuse deve essere corrispondente!"
    }

    val result = evalRecursive(expression)

    result.let { println("Il risultato è $it") }
}

///////////////////////////////////////////////////////////////////////////
// Iterative
///////////////////////////////////////////////////////////////////////////

data class EvaluationState(
    val operands: MutableList<Double> = mutableListOf(),
    val operators: MutableList<Operator> = mutableListOf(),
    val digits: MutableList<Char> = mutableListOf()
)

fun eval(expression: String) = expression.fold(EvaluationState()) { state, char ->
    state.apply {
        when (char) {
            '(' -> Unit
            ')' -> {
                val (first, second) = operands.run { removeAt(lastIndex) to removeAt(lastIndex) }
                val r = operators.run { removeAt(lastIndex) }(second, first)
                operands.add(r)
            }
            '+' -> operators.add(::sum)
            '-' -> operators.add(::sub)
            '*', 'x' -> operators.add(::mul)
            '/', '÷' -> operators.add(::div)
            in '0'..'9', ',', '.' -> digits.add(char)
            ' ' -> {
                digits.takeIf { it.isNotEmpty() }
                    ?.joinToString(separator = "")
                    ?.toDoubleOrNull()
                    ?.let { operands.add(it) }
                    ?.also { digits.clear() }
            }
            else -> error("Carattere invalido nell'espressione: $char")
        }
    }
}.operands.first()

///////////////////////////////////////////////////////////////////////////
// Recursive
///////////////////////////////////////////////////////////////////////////

fun evalRecursive(expression: String): Double {
    // Exit condition.
    // If the expression is not surrounded by brackets, means that is a
    // simple number, and so convert it to Double and return it.
    if (!expression.startsWith('(') && !expression.endsWith(')')) {
        return expression.toDoubleOrNull() ?: error("Invalid expression")
    }

    // first bracket, space, operator
    val operatorChar = expression[2]

    val operator = when (operatorChar) {
        '+' -> ::sum
        '-' -> ::sub
        '*', 'x' -> ::mul
        '/', '÷' -> ::div
        else -> error("Invalid operator")
    }
    val (first, second) = getSubExpressions(expression)
    return operator(evalRecursive(first), evalRecursive(second))
}

fun getSubExpressions(expression: String): Pair<String, String> {

    // ( + `bracket or number`
    // 01234
    val tokenIndex = 4
    val token = expression[tokenIndex]

    return if (token == '(') {
        val (lastExpressionBracketIndex) = expression
            // Get the substring which excludes the opening bracket.
            .substring(tokenIndex + 1)
            .foldIndexed(0 to 1) { index, acc, c ->
                val (lastIndex, opens) = acc
                if (opens > 0 && c == ')') {
                    index to opens - 1
                } else if (opens > 0 && c == '(') {
                    lastIndex to opens + 1
                } else {
                    acc
                }
            }
        expression.substring(tokenIndex, lastExpressionBracketIndex + 1 + tokenIndex + 1) to
            expression.substring(lastExpressionBracketIndex + 1 + tokenIndex + 2).dropLast(2)
    } else {
        val (first, second) = expression
            .substring(tokenIndex)
            .split(" ")
            .take(2)

        first to second
    }
}