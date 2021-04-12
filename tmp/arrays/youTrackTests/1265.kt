// Original bug: KT-36310

private inline fun f(crossinline ci: () -> Unit) = object : R() {
    override fun g() {
        ci()
    }
}

private val prop = f {}

open class R {
    open fun g() {}
}

fun main() {
}
