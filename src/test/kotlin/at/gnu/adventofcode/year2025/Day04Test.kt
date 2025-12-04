package at.gnu.adventofcode.year2025

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day04Test {

    private val positions = listOf("..@@.@@@@.", "@@@.@.@.@@", "@@@@@.@.@@", "@.@@@@..@.", "@@.@@@@.@@", ".@@@@@@@.@",
        ".@.@.@.@@@", "@.@@@.@@@@", ".@@@@@@@@.", "@.@.@@@.@.")

    private val test = mapOf(
        Day04::part1 to 13,
        Day04::part2 to 43
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day04 = Day04(positions)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day04) }
            println("Day04::${function.name}: ${positions.size} positions -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
