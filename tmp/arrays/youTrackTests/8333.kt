// Original bug: KT-22060

open class Foo
class Bar: Foo()

fun main(args: Array<String>) {
    val b = Bar()
    val s = hashSetOf<Bar>(b, Bar())
    val f : Foo = b
    if(f in s)
    	jjj(f as Bar)
}

fun jjj(b: Bar) {
    println(b)
}
