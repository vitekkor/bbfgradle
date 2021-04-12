// Original bug: KT-28057

val seq = listOf(1, 2, 3, 4, 5).asSequence().map { i -> i * 2 + 128 }.filter { it % 21 == 0 }
    .mapIndexed { index, i -> println("I'm number $i at index $index") }
