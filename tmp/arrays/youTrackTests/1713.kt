// Original bug: KT-8120

fun main() {
    val indent = ">"

    class IndentedString(s: String) {
        val str = indent + s
        constructor(): this("[shouldn't be printed]")
        fun indent() = IndentedString(str)
        override fun toString() = str
    }

    val line = IndentedString("hi")
    println(line)
    println(line.indent())
}
