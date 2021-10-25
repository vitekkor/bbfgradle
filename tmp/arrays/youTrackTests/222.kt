// Original bug: KT-31656

fun usage1() {
    "".foo(
            1,
            "one",
            "two")
}

fun usage2() {
    "".foo(
            2,
            "one",
            "two")
}

private fun String.foo(
        i: Int, // Actual value of parameter `i` is always `one`
        vararg some: String) {
    println("$i $some")
}
