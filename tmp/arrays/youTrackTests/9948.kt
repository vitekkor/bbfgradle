// Original bug: KT-10646

val x1 = { if (true) 1 else "" } // no warning
val x2 = { if (true) 1 else println() } // no warning

val y1 = if (true) 1 else "" // IMPLICIT_CAST_TO_UNIT_OR_ANY
val y2 = if (true) 1 else println() // IMPLICIT_CAST_TO_UNIT_OR_ANY
