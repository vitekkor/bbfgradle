// Original bug: KT-28840

fun foo(): Boolean {
    val prop = System.getProperties()["foo"]
    if (prop !is String) {
        return false
    }

    return bar()
}

fun bar() = true
