// Original bug: KT-34488

@file:JvmName("KT-34488")

fun demo(s: String) {
    run {
        scope {
            "test" {}
            "test" { require(it != s) }
        }
    }
}

fun scope(block: () -> Unit) {}

operator fun String.invoke(block: (String) -> Unit) {}
