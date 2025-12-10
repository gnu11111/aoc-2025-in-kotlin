package at.gnu.adventofcode.year2025

import kotlin.time.measureTimedValue

class Day06(val numbersText: List<String>, val operatorsText: String) {

    fun part1(): Long {
        val numbers = numbersText.map { it.trim().split("""\s+""".toRegex()).map(String::toInt) }
        val operators = operatorsText.trim().split("""\s+""".toRegex()).map { it.first() }
        var total = 0L
        for (column in numbers.first().indices) {
            val operator = operators[column]
            var result = if (operator == '+') 0L else 1L
            for (row in numbers) {
                if (operator == '+')
                    result += row[column]
                else
                    result *= row[column]
            }
            total += result
        }
        return total
    }

    fun part2(): Long {
        val maxX = numbersText.maxOf { it.length }
        var operator = '+'
        var total = 0L
        val numbers = mutableListOf<Long>()
        for (x in 0..maxX) {
            val newOperator = operatorsText.getOrElse(x) { operator }
            if (newOperator in setOf('+', '*'))
                operator = newOperator
            var number = 0L
            for (y in 0 until numbersText.size) {
                val char = numbersText[y].getOrElse(x) { ' ' }
                if (char in '1'..'9')
                    number = if (number == 0L) char.digitToInt().toLong() else (number * 10L) + char.digitToInt().toLong()
            }
            if (number != 0L)
                numbers += number
            else {
                total += if (operator == '+')
                    numbers.sum()
                else
                    numbers.fold(1L) { acc, it -> it * acc }
                numbers.clear()
            }
        }
        return total
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2025/Day06.txt"
    }
}

fun main() {
    val input = Day06::class.java.getResource(Day06.RESOURCE)!!.readText().split("\n", "\r\n").filter { it.trim().isNotEmpty() }
    val day06 = Day06(input.dropLast(1), input.last())
    measureTimedValue { day06.part1() }.let { (result, time) -> println("Day06::part1 -> $result [$time]") }
    measureTimedValue { day06.part2() }.let { (result, time) -> println("Day06::part2 -> $result [$time]") }
}
