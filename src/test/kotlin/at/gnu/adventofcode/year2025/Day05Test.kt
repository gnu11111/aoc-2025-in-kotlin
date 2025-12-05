package at.gnu.adventofcode.year2025

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.measureTimedValue

class Day05Test {

    private val freshIngredients = listOf("3-5", "10-14", "16-20", "12-18")
    private val ingredients = listOf(1L, 5L, 8L, 11L, 17L, 32L)

    private val test = mapOf(
        Day05::part1 to 3L,
        Day05::part2 to 14L
    )

    @Test
    fun testMySolution() {
        for (function in test.keys) {
            val (result, time) = measureTimedValue {
                function.invoke(Day05(freshIngredients, ingredients))
            }
            println("Day05::${function.name}: ${freshIngredients.size} fresh "
                + "ingredients ranges, ${ingredients.size} ingredients -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
