// Original bug: KT-11634

interface A

open class Base {
    val a = object : A {}
}

class Derived : Base() {
    val x = object : A by super.a {}

    // This works:
    // val x = object : A by a {}
}
