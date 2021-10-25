// Original bug: KT-18340

val g = if (true) 1 else 2.0 // [IMPLICIT_CAST_TO_ANY] Conditional branch result of type Double is implicitly cast to Any
val h = (null as String?) ?: 1 // No warning
