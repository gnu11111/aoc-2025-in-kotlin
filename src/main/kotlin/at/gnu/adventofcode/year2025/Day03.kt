package at.gnu.adventofcode.year2025

import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day03(val banks: List<String>) {

    fun part1(): Long =
        calculateJoltage()

    fun part2(): Long =
        calculateJoltage(digits = 12)


    private fun calculateJoltage(digits: Int = 2) =
        banks.sumOf { bank ->
            (digits downTo 1).fold(0L to 0) { (joltage, firstIndex), digit ->
                val batteriesToConsider = bank.substring(firstIndex, bank.length - digit + 1)
                val (batteryIndex, battery) = batteriesToConsider.withIndex().maxBy { it.value }
                ((10L * joltage) + battery.digitToInt()) to (firstIndex + batteryIndex + 1)
            }.first
        }

    companion object {
        const val RESOURCE = "/adventofcode/year2025/Day03.txt"
    }
}

@ExperimentalTime
fun main() {
    val day03 = Day03(Day03::class.java.getResource(Day03.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    measureTimedValue { day03.part1() }.let { (result, time) -> println("Day03::part1 -> $result [$time]") }
    measureTimedValue { day03.part2() }.let { (result, time) -> println("Day03::part2 -> $result [$time]") }
}
