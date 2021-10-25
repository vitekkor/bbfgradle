// Original bug: KT-35466

import java.util.stream.IntStream

fun main(args: Array<String>) {
    IntStream.iterate(1) { n: Int -> n + 1 }
        .skip(5)
        .limit(10)
        .forEach { x: Int -> println(x) }
}
