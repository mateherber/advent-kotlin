import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        val length = input.first().length
        val numbers = input.binaryToInt()
        val gamma = sequence {
            var value = 1
            yield(value)
            for (i in 2..length) {
                value *= 2
                yield(value)
            }
        }.toList().reversed().sumOf { position ->
            if (numbers.count { it and position > 0 } > numbers.size / 2) position else 0
        }
        return gamma * (gamma.inv() and 2.0.pow(length.toDouble()).toInt() - 1)
    }

    fun part2(input: List<String>): Int {
        val length = input.first().length
        val numbers = input.binaryToInt()
        val positions = sequence {
            var value = 1
            yield(value)
            for (i in 2..length) {
                value *= 2
                yield(value)
            }
        }.toList().reversed()
        var position = 0
        var oxygen = numbers
        while (oxygen.size > 1) {
            val popular =
                if (oxygen.count { it and positions[position] > 0 } >= oxygen.size / 2.0) positions[position] else 0
            oxygen = oxygen.filter { item -> item and positions[position] == popular }
            position++
        }
        position = 0
        var co2 = numbers
        while (co2.size > 1) {
            val popular =
                if (co2.count { it and positions[position] > 0 } >= co2.size / 2.0) 0 else positions[position]
            co2 = co2.filter { item -> item and positions[position] == popular }
            position++
        }
        return oxygen.first() * co2.first()
    }

    val testInput = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = "Day03".inputStrings()
    println(part1(input))
    println(part2(input))
}


