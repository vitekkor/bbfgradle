// Original bug: KT-12559

class Outer {
    val x = 42
    @JvmField val y = 43

    inner class Inner {
        fun foo() = x // INVOKEVIRTUAL test/Outer.getX ()I
        fun bar() = y // GETFIELD test/Outer.y : I
    }
}
