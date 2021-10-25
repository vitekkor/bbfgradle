// Original bug: KT-23134

fun main(args: Array<String>) {
    val l = { i: Int -> println("foo") } // `i` is reported as unused, with UNUSED_ANONYMOUS_PARAMETER
    listOf(1).forEach (l::invoke)
}
