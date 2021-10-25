// Original bug: KT-31275

class SomeBuilder() {
    @Deprecated("", ReplaceWith("add(this)"), DeprecationLevel.ERROR)
    operator fun String?.unaryPlus() {
        TODO()
    }

    fun add(s: String?) {
        TODO()
    }
}

fun someBuild(action: SomeBuilder.() -> Unit) {
    val b = SomeBuilder()
    b.action()
}
