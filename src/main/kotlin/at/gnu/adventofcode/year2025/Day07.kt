package at.gnu.adventofcode.year2025

import kotlin.time.measureTimedValue

class Day07(manifold: List<String>) {

    data class Pos(val x: Int, val y: Int)

    private val maxY = manifold.size

    private val start = manifold.let {
        val y = it.indexOfFirst { row -> row.indexOf('S') > 0 }
        val x = it[y].indexOf('S')
        Pos(x, y)
    }

    private val splitters = manifold.flatMapIndexed { y, row ->
        row.mapIndexedNotNull { x, c -> if (c == '^') Pos(x, y) else null }
    }


    fun part1(): Long =
        traceRay(start).second.size.toLong()

    fun part2(): Long =
        traceRay(start).first


    private fun traceRay(start: Pos, memo: MutableMap<Pos, Pair<Long, Set<Pos>>> = mutableMapOf(),
                         rays: Long = 1L): Pair<Long, Set<Pos>> {
        memo[start]?.let { return memo[start]!! }
        var y = start.y
        while (++y < maxY) {
            val pos = Pos(start.x, y)
            if (pos.isSplitter()) {
                val left = traceRay(Pos(start.x - 1, y), memo)
                val right = traceRay(Pos(start.x + 1, y), memo)
                val positions = setOf(pos) + left.second + right.second
                return ((left.first + right.first) to positions).also { memo[start] = it }
            }

        }
        return rays to emptySet()
    }

    private fun Pos.isSplitter(): Boolean =
        splitters.any { (it.x == x) && (it.y == y) }


    companion object {
        const val RESOURCE = "/adventofcode/year2025/Day07.txt"
    }
}

fun main() {
    val day07 = Day07(Day07::class.java.getResource(Day07.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    measureTimedValue { day07.part1() }.let { (result, time) -> println("Day07::part1 -> $result [$time]") }
    measureTimedValue { day07.part2() }.let { (result, time) -> println("Day07::part2 -> $result [$time]") }
}
