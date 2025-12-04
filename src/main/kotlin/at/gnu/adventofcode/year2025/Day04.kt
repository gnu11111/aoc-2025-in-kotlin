package at.gnu.adventofcode.year2025

import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day04(val positions: List<String>) {

    private val paperRolls = positions.flatMapIndexed { y, row ->
        row.mapIndexedNotNull { x, c ->
            if (c == '@') PaperRoll(x, y) else null
        }
    }

    data class PaperRoll(val x: Int, val y: Int)


    fun part1(): Int =
        paperRolls.count { paperRolls.neighbors(it).size < 4 }

    fun part2(): Int {
        var count = 0
        val remaining = paperRolls.toMutableList()
        var remove = paperRolls
        while ((count++ < paperRolls.size) && remove.isNotEmpty()) {
            remove = remaining.filter { remaining.neighbors(it).size < 4 }
            remaining.removeAll(remove)
        }
        return paperRolls.size - remaining.size
    }


    private fun List<PaperRoll>.neighbors(roll: PaperRoll): Set<PaperRoll> =
        setOfNotNull(paperRoll(roll.x - 1, roll.y - 1), paperRoll(roll.x, roll.y - 1),
            paperRoll(roll.x + 1, roll.y - 1), paperRoll(roll.x - 1, roll.y), paperRoll(roll.x + 1, roll.y),
            paperRoll(roll.x - 1, roll.y + 1), paperRoll(roll.x, roll.y + 1), paperRoll(roll.x + 1, roll.y + 1))

    private fun List<PaperRoll>.paperRoll(x: Int, y: Int): PaperRoll? =
        firstOrNull { (it.x == x) && (it.y == y) }

    @Suppress("unused")
    private fun List<PaperRoll>.display() {
        for (y in 0 until positions.size) {
            for (x in 0 until positions.first().length)
                if (paperRoll(x, y) != null) print("@") else print(".")
            println()
        }
        println()
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2025/Day04.txt"
    }
}


@ExperimentalTime
fun main() {
    val day04 = Day04(Day04::class.java.getResource(Day04.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    measureTimedValue { day04.part1() }.let { (result, time) -> println("Day04::part1 -> $result [$time]") }
    measureTimedValue { day04.part2() }.let { (result, time) -> println("Day04::part2 -> $result [$time]") }
}
