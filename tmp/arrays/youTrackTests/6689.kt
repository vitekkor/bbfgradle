// Original bug: KT-25446

fun foo(): String? {
    run {
        if (true) return@run

        if (true) {
            0
        } else
            if (true) return null // if should be considered as statement. Error only in NI
    }

    return ""
}
