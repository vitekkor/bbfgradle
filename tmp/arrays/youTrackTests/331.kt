// Original bug: KT-45446

fun remove() {
    throw RuntimeException()
    var posV = 0

    debug {
        check(posV == 1)
    }

}

private fun debug(f: Runnable) {
    f.run()
}
