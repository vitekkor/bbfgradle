// Original bug: KT-43672

object Host {
    var y = 1
    var yy: Int
        @JvmStatic
        get() = y
        set(value) {}
}

val c_yy = Host::yy

fun main() {
    println(c_yy)
}
