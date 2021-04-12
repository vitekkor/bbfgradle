// Original bug: KT-33580

abstract class Demo {
    open var attribute = "a"
        protected set
}

class Child : Demo() {
    // public is required here
    public override var attribute = super.attribute
}

fun main() {
    val c = Child()
    c.attribute = "test" // wouldn't compile without public modifier!
}
