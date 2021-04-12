// Original bug: KT-27357

inline class InlineClass(val x: Int)

fun takeInline(inlineClass: InlineClass) = 1 // "unused"
fun takeUInt(c: UInt) = 1 // "unused"
fun takeInt(c: Int) = 1 // ok

val call = arrayOf(takeInline(InlineClass(1)), takeUInt(1u), takeInt(1))
