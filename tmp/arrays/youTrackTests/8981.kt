// Original bug: KT-17422

class `My bad class` {
    fun foo() {}
}
fun main(args: Array<String>) {

    val strangeName = `My bad class`()
    println(strangeName)
    strangeName.foo()
    val strangeNameObjectMethodRef = strangeName::foo
    // OK, but:

    //val strangeNameRef = ::`My bad class` //badaboom #1
    //val strangeNameMethodRef = `My bad class`::foo //badaboom #2
}
