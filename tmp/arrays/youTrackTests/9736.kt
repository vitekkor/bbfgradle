// Original bug: KT-5385

interface Foo
fun foo(): Foo? = null

public val foo: Foo = run {
    val x = foo()
    if (x == null) throw Exception() // return@run foo()!! has the same effect here
    x
}
