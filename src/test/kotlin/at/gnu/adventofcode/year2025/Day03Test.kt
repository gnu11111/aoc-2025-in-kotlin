package at.gnu.adventofcode.year2025

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day03Test {

    private val banks = listOf("987654321111111", "811111111111119", "234234234234278", "818181911112111")

    private val test = mapOf(
        Day03::part1 to 357L,
        Day03::part2 to 3121910778619L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day03 = Day03(banks)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day03) }
            println("Day03::${function.name}: ${banks.size} entries -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
