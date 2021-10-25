// Original bug: KT-16263

fun main(args: Array<String>) {
    Class.forName("kotlin.text.StringsKt").getMethods().single { it.name == "capitalize" && it.parameterTypes.size == 1 }.invoke(null, "")
}
