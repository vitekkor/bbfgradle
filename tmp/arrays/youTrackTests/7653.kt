// Original bug: KT-26467

inline class IC(val value: Any?) {
    override fun toString() = "IC($value)"
}

data class Box<T>(var ref: T)

fun main(args: Array<String>) {
    val ic = IC("OK")
    val box = Box<Any?>(null)
    box.ref = ic // SHOULD BOX HERE !!!
    println(box)
}
