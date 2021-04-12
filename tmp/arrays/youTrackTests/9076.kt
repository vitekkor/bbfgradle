// Original bug: KT-16655

fun main(args: Array<String>) {
    q.f()
    q.g()
}

object q {
    val f = make {println("I am f")}
    val g = make {println("I am g")}

    class make(val block: () -> Unit) {
        operator fun invoke() = block()
    }

    fun g() = println("I was forgotten to be deleted, and now they call me")
}
