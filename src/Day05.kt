fun main() {

    fun List<Pair<Int, Int>>.generatePoints() = sequence {
        if (get(0).second == get(1).second) {
            for (i in get(0).first..get(1).first) {
                yield(Pair(i, get(0).second))
            }
            return@sequence
        }
        if (get(0).first == get(1).first) {
            for (i in get(0).second..get(1).second) {
                yield(Pair(get(0).first, i))
            }
            return@sequence
        }
        if (get(0).second > get(1).second) {
            for (i in get(0).first..get(1).first) {
                yield(Pair(i, get(1).second + get(1).first - i))
            }
            return@sequence
        }
        for (i in get(0).first..get(1).first) {
            yield(Pair(i, i + get(1).second - get(1).first))
        }
    }

    fun part1(input: List<String>): Int {
        val lines = input.map { line ->
            line.split(" -> ").let { chunks ->
                listOf(chunks[0].parseIntPair(), chunks[1].parseIntPair()).sortedBy { it.first }.sortedBy { it.second }
            }
        }
        val straight = lines.filter { it[0].first == it[1].first || it[0].second == it[1].second }
        val points = straight.map { it.generatePoints().toList() }.flatten()
        return points.groupingBy { it }.eachCount().filter { it.value > 1 }.count()
    }

    fun part2(input: List<String>): Int {
        val lines = input.map { line ->
            line.split(" -> ").let { chunks ->
                listOf(chunks[0].parseIntPair(), chunks[1].parseIntPair()).sortedBy { it.second }.sortedBy { it.first }
            }
        }
        val pointList = lines.map { it.generatePoints().toList() }
        val points = pointList.flatten()
        return points.groupingBy { it }.eachCount().filter { it.value > 1 }.count()
    }

    val testInput = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2"
    )
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = "Day05".inputStrings()
    println(part1(input))
    println(part2(input))
}
