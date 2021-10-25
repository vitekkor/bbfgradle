// Original bug: KT-13912

fun compilerNPE1() {
    run {
        if (false) {
        }
    }
}

fun thisWorks1() {
    run {
        if (false) {
            println("non-empty block")
        }
    }
}

fun compilerNPE2() {
    1.let {
        if (false) {
        }
    }
}

fun thisWorks2() {
    1.let {
        if (false) {
            println("non-empty block")
        }
    }
}
