package at.gnu.adventofcode.year2025

class Day03(val banks: List<String>) {

    fun part1(): Long {
        var jolts = 0L
        for (bank in banks) {
            val sorted = bank.toCharArray().sorted().reversed()
            val largest = sorted.first().toString()
            val largestIndex = bank.indexOf(largest)
            val rest = bank.substring(largestIndex + 1)
            val restSorted = rest.toCharArray().sorted().reversed()
            val largestFromRest = if (restSorted.isNotEmpty()) restSorted.first().toString() else 0
            val secondLargest = sorted.drop(1).first().toString()
            val secondLargestIndex = bank.indexOf(secondLargest)
            val plus = when {
                largest == secondLargest -> (largest + secondLargest).toInt()
                largestIndex == (bank.length - 1) -> (secondLargest + largest).toInt()
                largestIndex > secondLargestIndex -> (largest + largestFromRest).toInt()
                else -> (largest + secondLargest).toInt()
            }
            jolts += plus
        }
        return jolts
    }

    fun part2(): Long {
        var totalOutputJoltage = 0L
        for (bank in banks) {
            var batteries = bank
            var joltage = 0L
            for (i in 12 downTo 1) {
                val batteriesToConsider = batteries.dropLast(i - 1)
                val largestBattery = batteriesToConsider.toCharArray().sorted().reversed().first()
                val largestBatteryIndex = batteriesToConsider.indexOf(largestBattery)
                joltage = (10L * joltage) + largestBattery.digitToInt()
                batteries = batteries.substring(largestBatteryIndex + 1)
            }
            totalOutputJoltage += joltage
        }
        return totalOutputJoltage
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2025/Day03.txt"
    }
}

fun main() {
    val day03 = Day03(Day03::class.java.getResource(Day03.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day03::part1 -> ${day03.part1()}")
    println("Day03::part2 -> ${day03.part2()}")
}
