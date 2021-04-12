// Original bug: KT-39898

fun <T> buildType(size: Int): T = TODO()

fun f() {
    buildMyList(5)
}

private fun buildMyList(count: Int): Unit {
    return buildType(size = count)
}
