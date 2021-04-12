// Original bug: KT-25573

open class Foo(val result: String)

fun test(s: String): String {
    // L captures "s", so L's constructor has additional String parameter in the bytecode
    class L(val i: Int) : Foo(s)

    val ctor = L::class.constructors.single()
    assert(ctor.parameters.size == 1)

    return ctor.call(s).result
}

fun main(args: Array<String>) {
    println(test("OK"))
}
