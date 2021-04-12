// Original bug: KT-34640

fun test(foo: String) {
    // some comments for "a"
    when (foo) {
        "1" -> println("1")
    }

    if (foo == "a") {
        // some comments for "a"
        println("a");
    }
}
