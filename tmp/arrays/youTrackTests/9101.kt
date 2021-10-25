// Original bug: KT-11833

// FILE: Father.kt

abstract class Father {
    protected abstract inner class InClass {
        abstract fun Method1();
    }
}

// FILE: Child.kt

class Child : Father() {
    protected val ChildInClass = object : Father.InClass() {
        override fun Method1() {
        }
    }
}
