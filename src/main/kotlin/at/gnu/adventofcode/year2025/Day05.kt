package at.gnu.adventofcode.year2025

import kotlin.time.measureTimedValue

class Day05(freshIngridients: List<String>, private val ingridients: List<Long>) {

    private val freshIngridients = freshIngridients.map {
        val (start, end) = it.split("-")
        start.toLong()..end.toLong()
    }


    fun part1(): Long =
        ingridients.count { ingredient -> freshIngridients.any { ingredient in it } }.toLong()

    fun part2(): Long {
        val ranges = freshIngridients.toMutableSet()
        val remove = freshIngridients.toMutableSet()
        while (remove.isNotEmpty()) {
            val add = mutableSetOf<LongRange>()
            remove.clear()
            for (range in ranges) {
                ranges.firstOrNull { (range != it) && (range.first in it) }?.let { start ->
                    add += start.first..start.last.coerceAtLeast(range.last)
                    remove += range
                }
                ranges.firstOrNull { (range != it) && (range.last in it) }?.let { end ->
                    add += range.first.coerceAtMost(end.first)..end.last
                    remove += range
                }
            }
            remove.removeAll(add)
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
    val freshIngridients = input.first().trim().split("\n", "\r\n")
    val ingridients = input.last().trim().split("\n", "\r\n").map(String::toLong)
    val day05 = Day05(freshIngridients, ingridients)
    measureTimedValue { day05.part1() }.let { (result, time) -> println("Day05::part1 -> $result [$time]") }
    measureTimedValue { day05.part2() }.let { (result, time) -> println("Day05::part2 -> $result [$time]") }
}
