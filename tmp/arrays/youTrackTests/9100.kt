// Original bug: KT-16377

class A() {
    var y: String? = null

    constructor(x: Any) : this() {
        println(if (x == "foo") "!!!" else { println(x as String); ">>>" })
    }
}
