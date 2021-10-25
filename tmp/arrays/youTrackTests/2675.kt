// Original bug: KT-41000

@file:OptIn(ExperimentalUnsignedTypes::class) // Note -- opt-in here

interface Foo {
    @ExperimentalUnsignedTypes
    val i: Int
}

abstract class B() : Foo {

    fun f() {
        println("$i") // Warning reported
        println(i) // Warning reported
    }
}

fun baz(f: Foo) {
    println(f.i) // Warning not reported
}
