// Original bug: KT-40448

val x = listOf(5, 6, 7, 8, 9)
    .asSequence()
    .filter { it > 1 }.filter { it > 2 }.filter { it > 3 }.filter { it > 4 }.filter { it > 5 }
    .maxOrNull()
