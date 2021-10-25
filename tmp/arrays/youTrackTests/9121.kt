// Original bug: KT-13765

fun foo(flag: Boolean) {
    var x: String? = null

    if (x == null) {
        x = if (flag) "34" else "12"
    }

    x.hashCode()
}

fun bar(flag: Boolean) {
    var x: String? = null

    if (x == null) {
        x = when {
            flag -> "34"
            else -> "12"
        }
    }

    x.hashCode()
}

fun baz(flag: Boolean) {
    var x: String? = null

    if (x == null) {
        x = if (flag) {
            "34"
        } else {
            "12"
        }
    }

    x.hashCode()
}

fun gav(flag: Boolean, arg: String?) {
    var x: String? = null

    if (x == null) {
        x = arg ?: if (flag) {
            "34"
        } else {
            "12"
        }
    }

    x.hashCode()
}

fun gau(flag: Boolean, arg: String?) {
    var x: String? = null

    if (x == null) {
        x = if (flag) {
            arg ?: "34"
        } else {
            arg ?: "12"
        }
    }

    x.hashCode()
}
