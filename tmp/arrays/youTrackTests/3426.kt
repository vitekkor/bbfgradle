// Original bug: KT-27838

val v3 = listOf(
    1, 2, 3,
    -11, -33, /*for some reason user wants
a multiline
comment here */
    25, 100,
    101, 102).firstOrNull { it % 2 == 0 }
