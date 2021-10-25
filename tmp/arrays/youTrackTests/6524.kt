// Original bug: KT-30032

// Here at all lines with comment "unreachable code" there is no diagnostic

fun test() {
    if (null is Int) {
        println() // unreachable code
    }

    if (null !is Int) {
        println()
    } else {
        println() // unreachable code
    }

    if (false) {
        println() // unreachable code
    } else {
        println()
    }

    while (false) {
        println() // unreachable code
    }
    
    when {
        null != null -> println() // unreachable code
        1 != 1 -> println() // unreachable code
        null is Int -> println() // unreachable code
    }
}
