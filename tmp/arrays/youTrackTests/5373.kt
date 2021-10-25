// Original bug: KT-27221

sealed class A
sealed class B : A()
sealed class C : B()
object BB : B()
object CC : C()

fun foo(a: A) {
    if (a is B) {
        if (a is C) {
            // 1.2.61: a is Smart cast to C; a's type is "C & B (smart cast from A)"
            // 1.2.70: a is Smart cast to B; a's type is "B & C (smart cast from A)"
            val t = when (a) { // 1.2.70: Error: 'when' expression must be exhaustive, add necessary 'BB' branch or 'else' branch instead
                is CC -> "CC"
            }
        }
    }
}
