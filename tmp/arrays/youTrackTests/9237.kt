// Original bug: KT-15581

abstract class Base {
    abstract val a: String
    val b: String = a
}

class Subclass : Base() {
    override val a = "foo"
}

class Subclass2 : Base() {
    override val a: String
        get() = "foo"
}
