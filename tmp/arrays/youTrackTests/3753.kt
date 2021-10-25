// Original bug: KT-37918

open class A(val s: String, val i: () -> Int, val i2: Int) {
    @Deprecated("Replace with primary constructor", ReplaceWith("C(s = \"\", a = { i }, m = i)"))
    constructor(i: Int) : this("", { i }, i)
}

class T: A {
    constructor(): super(33)
    constructor(i: Int): super(i)
}
