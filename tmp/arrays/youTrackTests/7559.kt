// Original bug: KT-25387

fun main(args: Array<String>) {
    "".split(Regex("")) // OK - KDoc, navigation to StringsJVM.kt
    "".split("") // WRONG - No docs, navigation to decompiled stub StringsKt.class
}
