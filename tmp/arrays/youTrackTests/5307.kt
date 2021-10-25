// Original bug: KT-21320

interface Checker {
    fun check(t: Any?)
}

inline fun <reified T> T.checker(): Checker = object : Checker {
    override fun check(t: Any?) {
        t as T
    }
}

fun main(args: Array<String>) {
    val checker1 = "a".checker()
    val checker2 = "b".checker()
    assert(checker1.javaClass != checker2.javaClass)
}
