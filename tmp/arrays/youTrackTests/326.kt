// Original bug: KT-45316

interface R

fun test(fn: R.() -> R) {
    val renderer = object : R {
        fun render(fn: R.() -> Unit) {
            fn() // AMBIGUITY
        }
    }
}
