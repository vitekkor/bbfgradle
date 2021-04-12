// Original bug: KT-35808

class Test {

    internal var a: String
    internal var b: String
    fun getA(): String {
        return b
    }

    init {
        a = "t"
        b = "s"
    }
}
