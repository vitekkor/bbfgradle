// Original bug: KT-21417

fun r(): String {
    run {
        return "hello"
    }
} // compiles OK

fun w(): String {
    while (true) {
        return "hello"
    }
} // compiles OK

fun wr(): String {
    while (true) {
        run {
            return "hello"
        }
    }
} // compiles OK

fun rw(): String {
    run {
        while (true) {
            return "hello"
        }
    }
} // error: a 'return' expression required in a function with a block body ('{...}')

fun swg(): String {
    run {
        while (true) {
            return "hello"
        }
        return "goodbye"
    }
} // compiles OK (but warns about unreachable code)

fun forever(): String {
    while(true) {
        // stuck forever!
    }
}

fun forever2(): String {
    run {
        while (true) {
            // stuck forever?
        }
    }
}
