// Original bug: KT-30242

fun foo(f: () -> Any) {}

fun test(b: Boolean) {
    foo {
        if (true) { // Error
            println("meh")
        }
    }
}

fun test2(b: Boolean) {
    foo {
        when { // Error
            b -> println("meh")
        }
    }
}
