// Original bug: KT-11733

interface Predicate<T>

fun <T> Predicate(x: (T?) -> Boolean): Predicate<T> = null!!

fun foo() {
    process(Predicate { x -> true }) // Error at x: cannot infer type parameter
}

fun process(x: Predicate<String>) {}
