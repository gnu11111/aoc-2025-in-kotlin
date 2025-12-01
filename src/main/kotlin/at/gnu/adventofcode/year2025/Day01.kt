package at.gnu.adventofcode.year2025

import kotlin.math.abs

class Day01(val rotations: List<String>) {

    fun part1(): Int {
        var position = START_POSITION
        var password = 0
        for (rotation in rotations) {
            val amount = Integer.valueOf(rotation.substring(1))
            val direction = if (rotation.startsWith("L")) -1 else 1
            position = (position + (direction * amount)).mod(DIAL_SIZE)
            if (position == 0) password++
        }
        return password
    }

    fun part2(): Int {
        var position = START_POSITION
        var password = 0
        for (rotation in rotations) {
            val amount = Integer.valueOf(rotation.substring(1))
            val direction = if (rotation.startsWith("L")) -1 else 1
            repeat(amount) {
                position = (position + direction).mod(DIAL_SIZE)
                if (position == 0) password++
            }
        }
        return password
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
