// Original bug: KT-36047

fun getANumber() = 2 // Or any number...

fun main() {
    when (val myNumber = getANumber()) {
        1 -> println("foo: $myNumber")
        2 -> println("bar: $myNumber")
        3 -> println("baz: $myNumber")
        else -> println("quux: $myNumber")
    }
}
