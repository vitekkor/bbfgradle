// Original bug: KT-30517

open class Foo(val args: String){
    constructor(arg: Any = 1) : this(arg.toString()) {
    }
}

open class Base(val baseArgs: String)
open class Bar(arg: Any = 1) : Base(arg.toString()) {
    val args = arg.toString()
}

object TF : Foo() {}
object TF2 : Foo(2) {}

fun main() {
    val f = object : Foo() {}
    val f2 = object : Foo(2) {}
    val b = object : Bar() {}
    println("f.args: " + f.args)
    println("f2.args: " + f2.args)
    println("TF.args: " + TF.args)
    println("TF2.args: " + TF2.args)
    println("b.args: " + b.args)
    println("b.baseArgs: " + b.baseArgs)
}
