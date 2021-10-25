// Original bug: KT-21320

interface Box<out T> {
    val value: T
}

inline fun <reified T> T.box(): Box<T> = object : Box<T> {
    override val value: T = this@box
}

fun main(args: Array<String>) {
    val box1 = 123.box()
    val box2 = 123.box()
    assert(box1.javaClass != box2.javaClass)
}
