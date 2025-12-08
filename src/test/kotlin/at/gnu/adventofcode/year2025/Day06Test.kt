package at.gnu.adventofcode.year2025

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.measureTimedValue

class Day06Test {

    private val numbers = """
        123 328  51 64 
         45 64  387 23 
          6 98  215 314
            """.trimIndent().split("\n")
    private val operators = "*   +   *   +  "

    private val test = mapOf(
        Day06::part1 to 4277556L,
        Day06::part2 to 3263827L
    )

    @Test
    fun testMySolution() {
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(Day06(numbers, operators)) }
            println("Day06::${function.name}: ${numbers.size} rows, " +
                    "${operators.length} columns -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
