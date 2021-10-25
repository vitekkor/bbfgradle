// Original bug: KT-24176

fun some(f: () -> Int): Int = f()
fun boo(): Int = 42

fun other() {
    println(
        some {
            boo()
            boo() // Hint is expected
        }
    )

    some {
        boo()
        boo() // Should be no hint according to this ticket
    }
}
