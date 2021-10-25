// Original bug: KT-22169

open class A {
    var parent = "default value"

    constructor(parent: String?, state: Int)
}

class Foo {
    inner class B : A {
        constructor(parent: String?, state: Int) : super(parent, state){
        }
    }
}

fun main(args: Array<String>) {
    println(Foo().B("ppp", 123).parent)
}
