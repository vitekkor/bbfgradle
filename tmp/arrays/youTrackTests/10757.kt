// Original bug: KT-1726

class Foo(
        var state : Int,
        val f : (Int) -> Int){

    fun next() : Unit {
        val nextState = f(state)
        state = nextState
    }
}

fun main(args : Array<String>) {
    val f = Foo(23, {x -> 2 * x})
    f.next()
}
