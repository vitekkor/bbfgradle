// Original bug: KT-29753

fun test(a: String, b: String?) {
    println("test1")
}

// it's more specific because second parameter "String" is more specific than "String?"
fun test(a: String, b: String, c: String = "c") {
    println("test2")
}

// Or the same:
private object Scope {
    fun test(a: String, b: String?) { // (1)
        println("test1")
    }

    @JvmName("other")
    fun test(a: String, b: String) { // (2)
        println("test2")
    }

    fun foo() {
        test("a", "b") // test is resolved to (2)
    }
}
