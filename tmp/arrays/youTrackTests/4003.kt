// Original bug: KT-28951

fun main(args: Array<String>) {
    println("Hello, world!")
    test?.applyN {
        println(this)
    }
    test = "NonNull"
    test?.applyN {
        println(this)
    }
}

var test: String? = null
fun <T> T.applyN(block: T.() -> Unit): Any {
    return this.apply(block) ?: empty()
}

fun empty() {
    println("empty")
}
