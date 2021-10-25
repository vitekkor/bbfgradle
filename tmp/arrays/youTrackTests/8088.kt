// Original bug: KT-5588

fun main(args: Array<String>) {
    test("don't!")
}

fun test(s: String?) {
    val id = when (s) {
        "a" -> 1
        else -> null
    }

    if (id == null) return
    println("Really??")
}
