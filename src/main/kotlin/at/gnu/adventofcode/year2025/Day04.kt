package at.gnu.adventofcode.year2025

import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day04(grid: List<String>) {

    data class PaperRoll(val x: Int, val y: Int)

    private val paperRolls = grid.flatMapIndexed { y, row ->
        row.mapIndexedNotNull { x, c -> if (c == '@') PaperRoll(x, y) else null }
    }


    fun part1(): Int =
        paperRolls.count { paperRolls.neighborsOf(it).size < 4 }

    fun part2(): Int {
        var count = 0
        val remainingRolls = paperRolls.toMutableList()
        var rollsToRemove = paperRolls
        while ((count++ < paperRolls.size) && rollsToRemove.isNotEmpty()) {
            rollsToRemove = remainingRolls.filter { remainingRolls.neighborsOf(it).size < 4 }
            remainingRolls.removeAll(rollsToRemove)
        }
        return paperRolls.size - remainingRolls.size
    }


    private fun List<PaperRoll>.neighborsOf(roll: PaperRoll): Set<PaperRoll> =
        setOfNotNull(paperRollAt(roll.x - 1, roll.y - 1), paperRollAt(roll.x, roll.y - 1),
            paperRollAt(roll.x + 1, roll.y - 1), paperRollAt(roll.x - 1, roll.y), paperRollAt(roll.x + 1, roll.y),
            paperRollAt(roll.x - 1, roll.y + 1), paperRollAt(roll.x, roll.y + 1), paperRollAt(roll.x + 1, roll.y + 1))

    private fun List<PaperRoll>.paperRollAt(x: Int, y: Int): PaperRoll? =
        firstOrNull { (it.x == x) && (it.y == y) }

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
