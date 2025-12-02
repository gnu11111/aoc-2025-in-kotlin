package at.gnu.adventofcode.year2025

class Day02(ranges: List<String>) {

    private val ranges = ranges.map {
        val startEnd = it.split("-")
        startEnd.first().toLong() to startEnd.last().toLong()
    }

    fun part1(): Long =
        ranges.fold(setOf<Long>()) { ids, range -> ids + range.findIllegalIDs() }.sum()

    fun part2(): Long =
        ranges.fold(setOf<Long>()) { ids, range -> ids + range.findIllegalIDs(allPeriods = true) }.sum()

    private fun Pair<Long, Long>.findIllegalIDs(allPeriods: Boolean = false): Set<Long> =
        (first..second).fold(setOf()) { ids, number ->
            if (number.toString().isIllegal(allPeriods)) ids + number else ids
        }

    private fun String.isIllegal(allPeriods: Boolean = false): Boolean {
        if (startsWith("0") || (!allPeriods && ((length % 2) != 0)))
            return false
        val half = length / 2
        val start = if (allPeriods) 1 else half
        for (blockLength in start..half) {
            if ((length % blockLength) != 0)
                continue
            val amountOfBlocks = length / blockLength
            var legal = false
            outer@ for (block in 1 until amountOfBlocks) {
                for (letter in 0 until blockLength) {
                    if (this[letter] != this[letter + (block * blockLength)]) {
                        legal = true
                        break@outer
                    }
                }
            }
            if (!legal) return true
        }
        return false
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2025/Day02.txt"
    }
}

fun main() {
    val day02 = Day02(Day02::class.java.getResource(Day02.RESOURCE)!!.readText().trim().split(","))
    println("Day02::part1 -> ${day02.part1()}")
    println("Day02::part2 -> ${day02.part2()}")
}
