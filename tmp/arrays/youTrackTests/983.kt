// Original bug: KT-42621

package test.k

import java.util.stream.Collectors
import java.util.stream.IntStream

fun main() {
    val x = IntStream.of(1, 1, 4, 5, 1, 4)
        .map { a: Int -> a + 1 }
        .map { a: Int -> a - 1 }
        .filter { a: Int -> a > 3 }
        .flatMap { a: Int -> IntStream.of(a, a, a) }
        .boxed()
        .collect(Collectors.toList())
}
