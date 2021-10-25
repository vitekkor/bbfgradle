// Original bug: KT-24160

fun main(args: Array<String>) {
    val dst = mutableListOf<String>()
    if (true) {
        dst += "a single string"
    } else {
        dst += listOf("one string", "another string")
    }
}
