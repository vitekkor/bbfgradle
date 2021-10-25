// Original bug: KT-28508

class A {
    val x: Int
    init {
        val t = this
        if (t.x == null) { // Default value x is null, t.x unsound smart cast to Nothing
            x = 11
            t.x.inv()
            println("unreachable code!") // is printed
        } else {
            x = 11
        }
    }
}
