// Original bug: KT-41051

open class Base {
    open fun setup() {}
    init { setup() }
}

inline class Z(val y: Int)

class Derived : Base {
    constructor() : super()
    override fun setup() {
        x = Z(1)
    }

    var x = Z(0)
}

fun box(): String {
    val d = Derived()
    if (d.x.y != 1) return "fail"
    return "OK"
}
