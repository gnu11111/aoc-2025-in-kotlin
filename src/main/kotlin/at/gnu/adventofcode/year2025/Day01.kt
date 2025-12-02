package at.gnu.adventofcode.year2025

class Day01(private val rotations: List<String>) {

    fun part1() = calculatePassword()

    fun part2() = calculatePassword(countZeroPasses = true)

    private fun calculatePassword(countZeroPasses: Boolean = false): Int {
        var position = START_POSITION
        var zeroesPassed = 0
        var endedOnZero = 0
        for (rotation in rotations) {
            val direction = if (rotation.startsWith("L")) -1 else 1
            val amount = Integer.valueOf(rotation.substring(1))
            repeat(amount) {
                position = (position + direction).mod(DIAL_SIZE)
                if (position == 0) zeroesPassed++
            }
            if (position == 0) endedOnZero++
        }
        return if (countZeroPasses) zeroesPassed else endedOnZero
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2025/Day01.txt"
        const val START_POSITION = 50
        const val DIAL_SIZE = 100
    }
}

fun main() {
    val day01 = Day01(Day01::class.java.getResource(Day01.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day01::part1 -> ${day01.part1()}")
    println("Day01::part2 -> ${day01.part2()}")
}
