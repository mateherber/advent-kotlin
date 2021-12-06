fun main() {
    fun part1(input: List<String>): Int {
        val draw = input[0].split(",").map { it.toInt() }
        val boards = input.drop(1).filter { it.isNotBlank() }.chunked(5).map { Bingo(it) }
        draw.forEach { number ->
            boards.forEach { bingo ->
                bingo.draw(number)
                val hasBingo = bingo.hasBingo
                if (hasBingo is Yes) {
                    return hasBingo.winner * bingo.unmarked
                }
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val draw = input[0].split(",").map { it.toInt() }
        val boards = input.drop(1).filter { it.isNotBlank() }.chunked(5).map { Bingo(it) }
        draw.forEach { number ->
            boards.forEach { bingo ->
                bingo.draw(number)
                val hasBingo = bingo.hasBingo
                if (hasBingo is Yes && boards.all { it.hasBingo is Yes }) {
                    return hasBingo.winner * bingo.unmarked
                }
            }
        }
        return 0
    }

    val testInput = listOf(
        "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
        "",
        "22 13 17 11  0",
        "8  2 23  4 24",
        "21  9 14 16  7",
        "6 10  3 18  5",
        "1 12 20 15 19",
        "",
        "3 15  0  2 22",
        "9 18 13 17  5",
        "19  8  7 25 23",
        "20 11 10 24  4",
        "14 21 16 12  6",
        "",
        "14 21 17 24  4",
        "10 16 15  9 19",
        "18  8 23 26 20",
        "22 11 13  6  5",
        "2  0 12  3  7",
    )
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = "Day04".inputStrings()
    println(part1(input))
    println(part2(input))
}

interface IBingo {
    fun draw(number: Int)
    val unmarked: Int
    val hasBingo: BingoResult
}

data class BingoCell(val x: Int, val y: Int, val number: Int, var drawn: Boolean)

class Bingo(rows: List<String>) : IBingo {
    private val cells = mutableMapOf<Int, BingoCell>()
    private val xIndex = listOf<MutableList<BingoCell>>(
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
        mutableListOf()
    )
    private val yIndex = listOf<MutableList<BingoCell>>(
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
        mutableListOf()
    )

    init {
        rows.forEachIndexed { i, r ->
            r.split("\\s".toRegex()).filter { it.isNotBlank() }.forEachIndexed { j, s ->
                val cell = BingoCell(j, i, s.toInt(), false)
                cells[s.toInt()] = cell
                xIndex[j] += cell
                yIndex[i] += cell
            }
        }
    }

    override fun draw(number: Int) {
        val matched = cells[number] ?: return
        matched.drawn = true
        if (xIndex[matched.x].all { it.drawn } || yIndex[matched.y].all { it.drawn }) {
            hasBingo = Yes(number)
        }
    }

    override val unmarked: Int
        get() = cells.values.sumOf { if (it.drawn) 0 else it.number }
    override var hasBingo: BingoResult = No
}

sealed class BingoResult
object No : BingoResult()
data class Yes(val winner: Int) : BingoResult()


