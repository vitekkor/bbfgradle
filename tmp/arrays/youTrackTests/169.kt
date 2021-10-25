// Original bug: KT-37368

fun main(args: Array<String>) {
    println(emitMain())
    val numberOfFunctions = args[0]!!.toInt()
    for (i in 0..numberOfFunctions) {
        println(emitFunction("f$i", (i..numberOfFunctions).toList().toIntArray()))
    }
}

fun emitMain() = """
    fun main() {
        println(f0())
    }
""".trimIndent()

fun emitFunction(name: String, calls: IntArray): String = buildString {
    appendln("fun $name(): Int {")
    for (callIdx in calls) {
        appendln("    println(f$callIdx())")
    }
    appendln("    return 42")
    appendln("}")
}
