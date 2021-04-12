// Original bug: KT-44273

package test

enum class En {
    A, B, C, D
}

fun testAny(a: Any) =
        when (a) {
            En.A -> "A"
            En.B -> "B"
            En.C -> "C"
            else -> "???"
        }

fun testEn(a: En) =
        when (a) {
            En.A -> "A"
            En.B -> "B"
            En.C -> "C"
            else -> "???"
        }
