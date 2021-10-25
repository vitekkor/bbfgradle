// Original bug: KT-27034

import java.util.function.Predicate

fun test(some: ArrayList<String>) {
    some.removeIf(Predicate {
        println()
        println()

        true
    })
}
