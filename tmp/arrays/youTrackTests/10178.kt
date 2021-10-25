// Original bug: KT-2108

fun <T> lock(callback: () -> T): T =
    try {
        callback()
    } finally {
    }

var count = 0

fun foo() =
    lock {
        count += 1 // ASSIGNMENT_TYPE_MISMATCH
    }
