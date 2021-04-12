// Original bug: KT-28554

interface Base {
    fun calc() : String
}

class Delegate : Base by Impl()

class Impl : Base {
    override fun calc(): String {
        return "impl"
    }
}

fun main(b: Delegate) {
    val s = b.calc()
}
