// Original bug: KT-36140

object Global {
    operator fun set(
	p: String,
        mode: String = "default",
        value: Int
    ) {
        println(p)
        println(mode)
        println(value)
    }
}

fun main() {
    Global["111"] = 333
}
