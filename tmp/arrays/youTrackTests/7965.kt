// Original bug: KT-20834

class Foo {

    init {
        //println(orange) does not compile
        println(Bar().color) // prints "null"
    }

    val orange:String = "orange"

    inner class Bar {
        val color:String = orange
    }
}

fun main(args: Array<String>) {
    Foo()
}
