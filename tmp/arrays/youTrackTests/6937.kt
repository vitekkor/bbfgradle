// Original bug: KT-21588

package aoc2016.day1

import kotlin.math.abs

const val input = "L3, R2, L5, R1, L1, L2, L2, R1, R5, R1, L1, L2, R2, R4, L4, L3, L3, R5, L1, R3, L5, L2, R4, L5, R4, R2, L2, L1, R1, L3, L3, R2, R1, L4, L1, L1, R4, R5, R1, L2, L1, R188, R4, L3, R54, L4, R4, R74, R2, L4, R185, R1, R3, R5, L2, L3, R1, L1, L3, R3, R2, L3, L4, R1, L3, L5, L2, R2, L1, R2, R1, L4, R5, R4, L5, L5, L4, R5, R4, L5, L3, R4, R1, L5, L4, L3, R5, L5, L2, L4, R4, R4, R2, L1, L3, L2, R5, R4, L5, R1, R2, R5, L2, R4, R5, L2, L3, R3, L4, R3, L2, R1, R4, L5, R1, L5, L3, R4, L2, L2, L5, L5, R5, R2, L5, R1, L3, L2, L2, R3, L3, L4, R2, R3, L1, R2, L5, L3, R4, L4, R4, R3, L3, R1, L3, R5, L5, R1, R5, R3, L1"

fun main(args: Array<String>)
{
    println(computePosition(prepare("R2, L3")).computeDistance())  // 5
    println(computePosition(prepare("R2, R2, R2")).computeDistance())  // 2
    println(computePosition(prepare("R5, L5, R5, R3")).computeDistance())  // 12

    // PART 1:
    println(computePosition(prepare(input)).computeDistance())
    // or alternatively:
    input
            .run(::prepare)
            .run(::computePosition)
            .run { computeDistance() }
    // or:
    val w = Wanderer()
    for (cmd in prepare(input)) {
        w.move(cmd)
    }
    println(w.position.computeDistance())
    // or:
    with (Wanderer()) {
        prepare(input).forEach(::move)
        println(position.computeDistance())
    }

}

fun prepare(input: String): List<Command> {
    return input
            .split(", ")
            .map { Command(extractDir(it), extractInt(it)) }
}

fun extractDir(s: String) = Turn.valueOf(s[0].toString())
fun extractInt(s: String) = Integer.parseInt(s.substring(1))

enum class Turn { L, R }
data class Command(val turn: Turn, val dist: Int)


fun computePosition(commands: List<Command>): Pair<Int, Int> {
    val NORTH = Pair(1, 0)
    val START_POS = Pair(0,0)
    return commands
            .fold(Pair(START_POS, NORTH)) { (pos, dir), cmd ->
                Pair(cmd.newPosition(pos, dir), cmd.newDir(dir))
            }
            .first
}

// Manhattan distance
fun Pair<Int, Int>.computeDistance() = abs(this.first) + abs(this.second)


fun Command.newDir(dir: Pair<Int, Int>): Pair<Int, Int> {
    return when(this.turn) {
        Turn.R -> Pair(-dir.second, dir.first)
        Turn.L -> Pair(dir.second, -dir.first)
    }
}

fun Command.newPosition(pos: Pair<Int, Int>, dir: Pair<Int, Int>): Pair<Int, Int> {
    return pos + newDir(dir) * dist
}

operator infix fun Pair<Int, Int>.plus(x: Pair<Int, Int>): Pair<Int, Int>
        = Pair(first+x.first, second+x.second)

infix operator fun Pair<Int, Int>.times(x: Int) = Pair(first*x, second*x)


class Wanderer {
    var position = Pair(0, 0)
    var direction = Pair(1, 0)
//    var history = mutableListOf(position)

    fun move(cmd: Command) {
        updateDirWith(cmd)
        updatePosWith(cmd)
    }

    private fun updateDirWith(cmd: Command) {
        direction = when (cmd.turn) {
            Turn.R -> Pair(-direction.second, direction.first)
            Turn.L -> Pair(direction.second, -direction.first)
        }
    }

    private fun updatePosWith(cmd: Command) {
        for (i in 0 until cmd.dist) {
            position += direction
            stepHappened()
        }
    }

    protected fun stepHappened() {}
}
