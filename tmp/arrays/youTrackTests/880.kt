// Original bug: KT-44978

inline class StringArray(val values: Array<String>) 

fun foo(a1: StringArray, a2: StringArray) {
    for ((i, a) in arrayOf(a1, a2).withIndex()) {
        println(a)
    }
}

fun main() {
    foo(StringArray(arrayOf("Hello")), StringArray(arrayOf("World")))
}
