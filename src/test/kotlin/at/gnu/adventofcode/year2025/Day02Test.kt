package at.gnu.adventofcode.year2025

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.measureTimedValue

class Day02Test {

    private val input = listOf("11-22", "95-115", "998-1012", "1188511880-1188511890", "222220-222224",
        "1698522-1698528", "446443-446449", "38593856-38593862", "565653-565659", "824824821-824824827",
        "2121212118-2121212124")

    private val test = mapOf(
        Day02::part1 to 1227775554L,
        Day02::part2 to 4174379265L
    )

    @Test
    fun testMySolution() {
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function.invoke(Day02(input)) }
            println("Day02::${function.name}: ${input.size} ranges -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
