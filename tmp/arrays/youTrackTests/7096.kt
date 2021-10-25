// Original bug: KT-28508

class A {
    val x: Int?
    init {
        val t = this
        if (t.x != null) { // t.x unsound smart cast to Int
            x = null
            println(t.x.inv()) // OK, but t.x is null
        } else {
            x = 11
        }
    }
}
