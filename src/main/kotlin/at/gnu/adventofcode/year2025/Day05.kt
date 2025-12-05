package at.gnu.adventofcode.year2025

import kotlin.time.measureTimedValue

class Day05(freshIngredients: List<String>, val ingredients: List<Long>) {

    private val freshIngredients = freshIngredients.map {
        val (start, end) = it.split("-")
        start.toLong()..end.toLong()
    }

    infix fun LongRange.overlaps(range: LongRange)= (last >= range.first) && (range.last >= first)
    infix fun LongRange.merge(range: LongRange) = first.coerceAtMost(range.first)..last.coerceAtLeast(range.last)


    fun part1() =
        ingredients.count { ingredient -> freshIngredients.any { ingredient in it } }.toLong()

    fun part2(): Long {
        val ranges = freshIngredients.toMutableSet()
        val remove = freshIngredients.toMutableSet()
        while (remove.isNotEmpty()) {
            val add = mutableSetOf<LongRange>()
            remove.clear()
            for (range in ranges) {
                ranges.firstOrNull { (it != range) && (it overlaps range) }?.let {
                    add += it merge range
                    remove += setOf(range, it)
                }
            }
            ranges.removeAll(remove)
            ranges.addAll(add)
        }
        return ranges.sumOf { it.last - it.first + 1L }
    }


    companion object {
        const val RESOURCE = "/adventofcode/year2025/Day05.txt"
    }
}

fun main() {
    val input = Day05::class.java.getResource(Day05.RESOURCE)!!.readText().trim().split("\n\n", "\r\n\r\n")
    val freshIngredients = input.first().trim().split("\n", "\r\n")
    val ingredients = input.last().trim().split("\n", "\r\n").map(String::toLong)
    val day05 = Day05(freshIngredients, ingredients)
    measureTimedValue { day05.part1() }.let { (result, time) -> println("Day05::part1 -> $result [$time]") }
    measureTimedValue { day05.part2() }.let { (result, time) -> println("Day05::part2 -> $result [$time]") }
}
