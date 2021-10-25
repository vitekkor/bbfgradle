// Original bug: KT-37280

interface A {
    fun fromA() {}
}

interface B {
    fun fromB() {}
}

fun test(a: A) {
    a as B // a now has type A & B

    a.fromA() // OK
    a.fromB() // OK
}
