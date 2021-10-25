// Original bug: KT-29282

fun WithCompanion.test() {
    object : WithCompanion(this) {}
}

open class WithCompanion(a: WithCompanion.Companion) {
    companion object
}

fun main(args: Array<String>) {
    WithCompanion(WithCompanion.Companion).test()
}
