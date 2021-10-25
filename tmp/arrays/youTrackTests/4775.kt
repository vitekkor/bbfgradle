// Original bug: KT-35210

annotation class Anno

fun box(a: List<Class<Anno>>, b: List<Class<out Anno>>) {
    assertEquals(a, ::box.annotations.map { it.annotationClass.java }) // error in NI, ok in OI
}

@Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
fun <@kotlin.internal.OnlyInputTypes T> assertEquals(expected: T, actual: T): T = TODO()
