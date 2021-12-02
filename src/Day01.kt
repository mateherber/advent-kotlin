fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = "Day01".inputInts()
    println(part1(input))
    println(part2(input))
}

fun part1(input: List<Int>): Int {
    return input.zipWithNext().count { (a, b) -> a < b }
}

fun part2(input: List<Int>): Int {
    return part1(input.windowed(3) { (a, b, c) -> a + b + c })
}
