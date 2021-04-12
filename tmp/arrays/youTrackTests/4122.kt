// Original bug: KT-18002

interface Property<T : Comparable<T>>

fun <T : Comparable<T>> parseAndApplyValue(property: Property<T>) {}

fun test(property: Property<*>) {
    parseAndApplyValue(property) // does not compile
}
