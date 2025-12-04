package at.gnu.adventofcode.year2025

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.measureTimedValue

class Day04Test {

    private val grid = listOf("..@@.@@@@.", "@@@.@.@.@@", "@@@@@.@.@@", "@.@@@@..@.", "@@.@@@@.@@", ".@@@@@@@.@",
        ".@.@.@.@@@", "@.@@@.@@@@", ".@@@@@@@@.", "@.@.@@@.@.")

    private val test = mapOf(
        Day04::part1 to 13,
        Day04::part2 to 43
    )

    @Test
    fun testMySolution() {
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(Day04(grid)) }
            println("Day04::${function.name}: ${grid.first().length}x${grid.size} grid -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
