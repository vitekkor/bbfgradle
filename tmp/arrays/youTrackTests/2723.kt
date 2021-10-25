// Original bug: KT-20750

class Foo
fun test1(x: Any?) = run { if (x is Foo) x else Foo() } // return type is Foo
fun test2(x: Any?) = run { if (x is Foo) return@run x else Foo() } // return type is Any?

fun test3(x: Any?): Foo = 
        run { if (x is Foo) return@run x else Foo() } // x has smart cast to Foo
