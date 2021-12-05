fun main() {
    fun part1(input: List<Pair<String, Int>>): Int {
        return input.groupingBy { it.first }.fold(0) { acc, item -> acc + item.second }.let { commands ->
            commands.getOrDefault("forward", 0) * (commands.getOrDefault("down", 0) - commands.getOrDefault("up", 0))
        }
    }

    fun part2(input: List<Pair<String, Int>>): Int {
        var aim = 0
        var depth = 0
        var position = 0
        input.forEach { (command, v) ->
            when (command) {
                "down" -> aim += v
                "up" -> aim -= v
                "forward" -> {
                    position += v
                    depth += aim * v
                }
            }
        }
        return depth * position
    }

    val testInput = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2").toStringIntPairs()
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = "Day02".inputStrings().toStringIntPairs()
    println(part1(input))
    println(part2(input))
}