// Original bug: KT-25289

open class Parent(i: Int) {
    companion object {
        fun fromParent(): Int = 42
    }
}

class Child : Parent(fromParent()) // or Parent.fromParent()
