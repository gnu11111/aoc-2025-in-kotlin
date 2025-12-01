package at.gnu.adventofcode.year2025

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day01Test {

    private val rotations = listOf("L68", "L30", "R48", "L5", "R60", "L55", "L1", "L99", "R14", "L82")

    private val test = mapOf(
        Day01::part1 to rotations to 3,
        Day01::part2 to rotations to 6,
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        for ((function, input) in test.keys) {
            val (result, time) = measureTimedValue { function(Day01(input)) }
            println("Day01::${function.name}: ${input.size} rotations -> $result [$time]")
            assertEquals(test[function to input], result)
        }
    }
}
