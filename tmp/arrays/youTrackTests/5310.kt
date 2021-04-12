// Original bug: KT-21319

inline fun receiver(crossinline action: () -> Any) {
    val o1 = { action() }()
    val o2 = { action() }()
    assert(o1.javaClass != o2.javaClass)
}

fun main(args: Array<String>) {
    receiver { object : Any() {} }
}
